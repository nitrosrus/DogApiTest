package com.example.dogapitest.mvp.presenter

import com.example.dogapitest.mvp.model.cache.IBreedsCache
import com.example.dogapitest.mvp.model.repo.ImageApiBreeds
import com.example.dogapitest.mvp.presenter.list.IImageListPresenter
import com.example.dogapitest.mvp.view.BreedsImageView
import com.example.dogapitest.mvp.view.list.ImageItemView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class ImagePresenter(val mainThreadScheduler: Scheduler, val listByImage: ArrayList<String>) :
    MvpPresenter<BreedsImageView>() {


    inner class ImageListPresenter : IImageListPresenter {
        val image = mutableListOf<String>()
        override var itemClickListener: ((ImageItemView) -> Unit)? = null
        override fun getCount() = image.size
        override fun bindView(view: ImageItemView) {
            val url = image[view.pos]
            view.loadImage(url)
        }

        override fun likeBTN(pos: Int) {
            setLike(pos)

        }

    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var imageApi: ImageApiBreeds

    @Inject
    lateinit var database: IBreedsCache

    val imageListPresenter = ImageListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
    }

    fun setLike(pos: Int) {
        database.putLikeImage(listByImage[0], imageListPresenter.image[pos]).observeOn(AndroidSchedulers.mainThread()).subscribe()
        //imageApi.cache.putLikeImage(listByImage[0], imageListPresenter.image[pos]).observeOn(mainThreadScheduler)
        //imageApi.setLikeImage(listByImage[0], imageListPresenter.image[pos])
        println(listByImage[0] + imageListPresenter.image[pos])
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
                    Timber.e(it)
                })
        }

    }


    fun backClicked(): Boolean {
        router.exit()
        return true
    }

}


