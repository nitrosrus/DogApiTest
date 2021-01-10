package com.example.dogapitest.mvp.presenter


import com.example.dogapitest.mvp.model.cache.IRoomFavouritesCache
import com.example.dogapitest.mvp.model.entity.RoomFavourites
import com.example.dogapitest.mvp.presenter.list.IFavouritesImageListPresenter
import com.example.dogapitest.mvp.view.FavouritesImageView
import com.example.dogapitest.mvp.view.list.FavouritesImageItemView
import com.example.dogapitest.rx.IRxProvider
import com.example.dogapitest.ui.network.NetworkStatus
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class FavouritesImagePresenter(val breed: String) :
    MvpPresenter<FavouritesImageView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var database: IRoomFavouritesCache

    @Inject
    lateinit var rxProvider: IRxProvider

    @Inject
    lateinit var networkStatus: NetworkStatus

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    val imageListPresenter = ImageListPresenter()

    inner class ImageListPresenter : IFavouritesImageListPresenter {
        var imageData = mutableListOf<String>()
        override var itemClickListener: ((Int) -> Unit)? = null
        override fun getCount() = imageData.size
        override fun bind(view: FavouritesImageItemView) {
            val url = imageData[view.pos]
            view.loadImage(url)
            view.setLikeEnable()
            view.setClickListener()
        }

    }


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadRoomDataBase()
        imageListPresenter.itemClickListener = { index -> logicUnCheckFavourite(index) }
    }

    private fun logicUnCheckFavourite(index: Int) {
        if (imageListPresenter.imageData.size != 1) {
            putDisLike(imageListPresenter.imageData[index])
            imageListPresenter.imageData.removeAt(index)
            updateData()
        } else {
            removeLastIndex(index)
        }

    }

    private fun removeLastIndex(index: Int) {
        putDisLike(imageListPresenter.imageData[index])
        imageListPresenter.imageData.removeAt(index)
        updateData()
        backClicked()
    }

    fun loadRoomDataBase() {
        database.getAllData().subscribeOn(rxProvider.ioThread())
            .observeOn(rxProvider.uiMainThread())
            .subscribe({ list ->
                convertData(list)
            }, {
                Timber.e(it)
            }).let { compositeDisposable.add(it) }
    }

    fun convertData(list: List<RoomFavourites>) {
        imageListPresenter.imageData.clear()
        list.forEach {
            if (it.breedName == breed) imageListPresenter.imageData.add(it.url)
        }
        updateData()
    }

    private fun putDisLike(url: String) {
        database.putDisLike(breed, url).observeOn(rxProvider.uiMainThread()).subscribe()
            .let { compositeDisposable.add(it) }

    }

    private fun updateData() = viewState.updateRVAdapter()

    private fun clearRx() = compositeDisposable.clear()

    override fun detachView(view: FavouritesImageView?) {
        clearRx()
        super.detachView(view)
    }

    fun backClicked(): Boolean {
        router.exit()
        return true
    }

}


