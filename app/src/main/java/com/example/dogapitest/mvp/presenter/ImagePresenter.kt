package com.example.dogapitest.mvp.presenter

import com.example.dogapitest.mvp.model.breedsModel.ImageBreedsList
import com.example.dogapitest.mvp.model.cache.IBreedsCache
import com.example.dogapitest.mvp.model.repo.ImageApiBreeds
import com.example.dogapitest.mvp.presenter.list.IImageListPresenter
import com.example.dogapitest.mvp.view.ImageView
import com.example.dogapitest.mvp.view.list.ImageItemView
import com.example.dogapitest.rx.IRxProvider
import com.example.dogapitest.ui.network.NetworkStatus
import io.reactivex.rxjava3.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class ImagePresenter(
    val breed: String,
    val subBreed: String?
) : MvpPresenter<ImageView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var imageApi: ImageApiBreeds

    @Inject
    lateinit var database: IBreedsCache

    @Inject
    lateinit var rxProvider: IRxProvider

    @Inject
    lateinit var networkStatus: NetworkStatus

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var breedsLikeStatus = mutableMapOf<String, List<String>>()

    private val name = subBreed ?: breed

    val imageListPresenter = ImageListPresenter()

    inner class ImageListPresenter : IImageListPresenter {
        val imageData = mutableListOf<String>()
        override var itemClickListener: ((ImageItemView) -> Unit)? = null
        override fun getCount() = imageData.size
        override fun bind(view: ImageItemView) {
            val url = imageData[view.pos]
            view.loadImage(url)
            checkAndSetLikeDislike(url, view)
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        checkInternet()
        imageListPresenter.itemClickListener = { view -> logicCheckLike(view) }
    }

    private fun checkAndSetLikeDislike(url: String, view: ImageItemView) {
        if (checkLikeInBase(url)) view.setLikeEnable() else view.setLikeDisable()
    }

    private fun checkLikeInBase(url: String): Boolean {

        breedsLikeStatus.values.forEach {
            if (it.contains(url)) return true
        }
        return false
    }

    private fun logicCheckLike(view: ImageItemView) {
        val url = imageListPresenter.imageData[view.pos]
        if (checkLikeInBase(url)) {
            view.setLikeDisable()
            breedsLikeStatus.remove(key = name, value = listOf(url))
            putDisLike(url)
        } else {
            view.setLikeEnable()
            breedsLikeStatus.put(key = name, value = listOf(url))
            putLike(url)
        }
    }


    private fun putLike(url: String) {
        compositeDisposable.add(
            database.putLikeImage(name, url).observeOn(rxProvider.uiMainThread()).subscribe()
        )
    }

    private fun putDisLike(url: String) {
        compositeDisposable.add(
            database.putDisLike(name, url).observeOn(rxProvider.uiMainThread()).subscribe()
        )
    }

    private fun checkInternet() {
        if (networkStatus.isOnline() == true) loadData() else viewState.serverErrorInternet()
    }

    fun loadRoomDataBase() {
        compositeDisposable.add(
            database.getAllLike()
                .observeOn(rxProvider.uiMainThread())
                .subscribe({ list ->
                    breedsLikeStatus.clear()
                    breedsLikeStatus.putAll(list)
                }, {
                    Timber.e(it)
                })
        )
    }

    private fun loadData() {
        loadRoomDataBase()
        if (subBreed == null) loadBreeds() else loadSubBreeds()

    }

    private fun loadBreeds() {
        compositeDisposable.add(
            imageApi.getImage(breed)
                .observeOn(rxProvider.uiMainThread())
                .subscribe({ list ->
                    convertData(list)
                }, {
                    Timber.e(it)
                    checkInternet()
                })
        )
    }

    private fun loadSubBreeds() {
        compositeDisposable.add(
            imageApi.getImage(breed, subBreed!!)
                .observeOn(rxProvider.uiMainThread())
                .subscribe({ list ->
                    convertData(list)
                }, {
                    Timber.e(it)
                    checkInternet()
                })
        )
    }

    private fun convertData(list: ImageBreedsList) {
        imageListPresenter.imageData.clear()
        imageListPresenter.imageData.addAll(list.message)
        updateData()
    }


    fun awaitNetworkStatus() {
        clearRx()
        compositeDisposable.add(
            networkStatus.isOnlineObserver()
                .subscribe { online -> if (online) loadData() }
        )
    }

    fun oneOrTwo() = subBreed.isNullOrEmpty()

    private fun updateData() = viewState.updateRVAdapter()

    private fun clearRx() = compositeDisposable.clear()


    override fun detachView(view: ImageView?) {
        clearRx()
        super.detachView(view)
    }


    fun backClicked(): Boolean {
        router.exit()
        return true
    }

}


