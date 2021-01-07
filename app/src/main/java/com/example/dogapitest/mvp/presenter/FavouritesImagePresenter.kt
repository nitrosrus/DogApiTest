package com.example.dogapitest.mvp.presenter


import com.example.dogapitest.mvp.model.cache.IBreedsCache
import com.example.dogapitest.mvp.presenter.list.IFavouritesImageListPresenter
import com.example.dogapitest.mvp.view.FavouritesImageView

import com.example.dogapitest.mvp.view.list.FavouritesImageItemView

import com.example.dogapitest.rx.IRxProvider
import com.example.dogapitest.ui.network.NetworkStatus
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class FavouritesImagePresenter(val breedsName: String) :
    MvpPresenter<FavouritesImageView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var database: IBreedsCache

    @Inject
    lateinit var rxProvider: IRxProvider

    @Inject
    lateinit var networkStatus: NetworkStatus

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var breedsLikeStatus = mutableMapOf<String, List<String>>()

    val imageListPresenter = ImageListPresenter()

    inner class ImageListPresenter : IFavouritesImageListPresenter {
        var imageData = listOf<String>()
        override var itemClickListener: ((FavouritesImageItemView) -> Unit)? = null
        override fun getCount() = imageData.size
        override fun bind(view: FavouritesImageItemView) {
            val url = imageData[view.pos]
            view.loadImage(url)
            view.setlike(checkLikeBase(url))
        }

    }


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadDataBase()

    }


    fun checkLikeBase(url: String): Boolean {
        breedsLikeStatus.values.forEach {
            if (it.contains(url)) return true
        }
        return false
    }


    fun logicCheckLike(view: FavouritesImageItemView): Boolean {
        val url = imageListPresenter.imageData[view.pos]
        val newUrl = listOf(url)

        return if (checkLikeBase(url)) {

            if (breedsLikeStatus[breedsName]?.size == 1)
                breedsLikeStatus.remove(breedsName, newUrl) else breedsLikeStatus.values.remove(
                newUrl
            )
            database.putDisLike(breedsName, url).observeOn(rxProvider.uiMainThread()).subscribe()
            false

        } else {


            breedsLikeStatus.put(breedsName, newUrl)
            putLike(view.pos)
            true
        }
    }

    fun putLike(pos: Int) {
        database.putLikeImage(breedsName, imageListPresenter.imageData[pos])
            .observeOn(AndroidSchedulers.mainThread()).subscribe()

    }

    fun loadDataBase() {

//        database.getAllLike().observeOn(rxProvider.uiMainThread()).subscribe({ list ->
//            breedsLikeStatus.clear()
//            breedsLikeStatus.putAll(list)
//            loadData()
//        }, {
//            Timber.e(it)
//        })
    }

    fun loadData() {
        imageListPresenter.imageData = breedsLikeStatus[breedsName]!!
        viewState.updateRVAdapter()
    }


    fun backClicked(): Boolean {
        router.exit()
        return true
    }


}


