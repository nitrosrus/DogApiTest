package com.example.dogapitest.mvp.presenter


import com.example.dogapitest.mvp.model.cache.IRoomFavouritesCache
import com.example.dogapitest.mvp.model.entity.RoomFavourites
import com.example.dogapitest.mvp.presenter.list.IFavouritesBreedsListPresenter
import com.example.dogapitest.mvp.view.FavouritesBreedsView
import com.example.dogapitest.mvp.view.list.FavouritesBreedsItemView
import com.example.dogapitest.navigation.Screens
import com.example.dogapitest.rx.IRxProvider
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class FavouritesBreedsPresenter() : MvpPresenter<FavouritesBreedsView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var database: IRoomFavouritesCache

    @Inject
    lateinit var rxProvider: IRxProvider

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    val breedsListPresenter = BreedsListPresenter()

    class BreedsListPresenter : IFavouritesBreedsListPresenter {

        val breedsData = mutableMapOf<String, Int>()
        override var itemClickListener: ((Int) -> Unit)? = null
        override fun getCount() = breedsData.size
        override fun bind(view: FavouritesBreedsItemView) {
            view.setClickListener()
            view.setBreed(breedsData.keys.elementAt(view.pos))
            setCountChecker(view)
        }

        private fun setCountChecker(view: FavouritesBreedsItemView) {
            if (breedsData.values.elementAt(view.pos) != 0) {
                view.setCountVisible()
                view.setCountBreed(breedsData.values.elementAt(view.pos).toString())
            } else view.setCountInvisible()
        }

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadRoomDataBase()
        breedsListPresenter.itemClickListener = {
            itemClick(it)
        }
    }

    private fun itemClick(index: Int) {
        router.navigateTo(Screens.LikeImageScreen(getBreedByIndex(index)))
    }

    private fun getBreedByIndex(index: Int) = breedsListPresenter.breedsData.keys.elementAt(index)

    fun loadRoomDataBase() {
        database.getAllData().subscribeOn(rxProvider.ioThread())
            .observeOn(rxProvider.uiMainThread())
            .subscribe({ list ->
                list?.let { convertData(it) }
            }, {
                Timber.e(it)
            }).let { compositeDisposable.add(it) }
    }

    private fun convertData(list: List<RoomFavourites>) {
        val favouritesDatabase = mutableMapOf<String, MutableList<String>>()
        favouritesDatabase.clear()
        list.forEach { favouritesDatabase[it.breedName] = mutableListOf() }
        list.forEach { favouritesDatabase[it.breedName]?.add(it.url) }
        favouritesDatabase.forEach { breedsListPresenter.breedsData.put(it.key, it.value.size) }
        updateData()
    }

    private fun updateData() = viewState.updateRVAdapter()

    override fun detachView(view: FavouritesBreedsView?) {
        clearRx()
        super.detachView(view)
    }

    private fun clearRx() = compositeDisposable.clear()

    fun backClicked(): Boolean {
        router.exit()
        return true
    }

}






