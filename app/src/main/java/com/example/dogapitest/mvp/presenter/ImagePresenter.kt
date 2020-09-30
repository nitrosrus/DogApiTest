package com.example.dogapitest.mvp.presenter

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.dogapitest.mvp.model.cache.IBreedsCache
import com.example.dogapitest.mvp.model.repo.ImageApiBreeds
import com.example.dogapitest.mvp.presenter.list.IImageListPresenter
import com.example.dogapitest.mvp.view.BreedsImageView
import com.example.dogapitest.mvp.view.list.ImageItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class ImagePresenter(val mainThreadScheduler: Scheduler, val listByImage: ArrayList<String>) :
    MvpPresenter<BreedsImageView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var imageApi: ImageApiBreeds

    @Inject
    lateinit var database: IBreedsCache

    private var breedsLikeStatus = mutableMapOf<String, List<String>>()

    val imageListPresenter = ImageListPresenter()

    inner class ImageListPresenter : IImageListPresenter {
        val image = mutableListOf<String>()
        override var itemClickListener: ((ImageItemView) -> Unit)? = null
        override fun getCount() = image.size
        override fun bindView(view: ImageItemView) {
            val url = image[view.pos]
            view.loadImage(url)
            view.setlike(checkLikeBase(url))
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun likeBTN(view: ImageItemView) {
            view.setlike(logicCheckLike(view))

        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadDataBase()
        loadData()
    }


    fun checkLikeBase(url: String): Boolean {
        breedsLikeStatus.values.forEach {
            if (it.contains(url)) return true
        }
        return false
    }



    @RequiresApi(Build.VERSION_CODES.N)
    fun logicCheckLike(view: ImageItemView): Boolean {
        val url = imageListPresenter.image[view.pos]
        val newUrl = listOf(url)
        return if (checkLikeBase(url)) {

            if (breedsLikeStatus[listByImage[0]]?.size == 1)
                breedsLikeStatus.remove(listByImage[0], newUrl) else breedsLikeStatus.values.remove(
                newUrl
            )
            database.putDisLike(listByImage[0], url)
            false

        } else {


            breedsLikeStatus.put(listByImage[0], newUrl)
            putLike(view.pos)
            true
        }
    }


    fun putLike(pos: Int) {
        database.putLikeImage(listByImage[0], imageListPresenter.image[pos])
            .observeOn(AndroidSchedulers.mainThread()).subscribe()

    }

    fun loadDataBase() {
        database.getAllLike().observeOn(mainThreadScheduler).subscribe({ list ->
            breedsLikeStatus.clear()
            breedsLikeStatus.putAll(list)
        }, {

            Timber.e(it)
        })
    }

    fun loadData() {
        if (listByImage.size == 1) {
            imageApi.getImage(listByImage[0]).observeOn(mainThreadScheduler)
                .subscribe({ list ->
                    imageListPresenter.image.clear()
                    imageListPresenter.image.addAll(list.message)
                    println(list.message)
                    viewState.updateList()
                }, {
                    viewState.serverErrorInternet()
                    Timber.e(it)
                })
        } else {
            imageApi.getImage(listByImage[0], listByImage[1]).observeOn(mainThreadScheduler)
                .subscribe({ list ->
                    imageListPresenter.image.clear()
                    imageListPresenter.image.addAll(list.message)
                    println(list.message)
                    viewState.updateList()
                    listByImage[0] = listByImage[1]
                }, {
                    viewState.serverErrorInternet()
                    Timber.e(it)
                })
        }
    }


    fun backClicked(): Boolean {
        router.exit()
        return true
    }

}


