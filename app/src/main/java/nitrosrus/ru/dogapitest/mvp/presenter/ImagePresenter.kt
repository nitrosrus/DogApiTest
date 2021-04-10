package nitrosrus.ru.dogapitest.mvp.presenter

import nitrosrus.ru.dogapitest.mvp.model.breedsModel.ImageBreedsList
import nitrosrus.ru.dogapitest.mvp.model.cache.IRoomFavouritesCache
import nitrosrus.ru.dogapitest.mvp.model.source.ApiImage
import nitrosrus.ru.dogapitest.mvp.presenter.list.IImageListPresenter
import nitrosrus.ru.dogapitest.mvp.view.ImageView
import nitrosrus.ru.dogapitest.mvp.view.list.ImageItemView
import nitrosrus.ru.dogapitest.rx.IRxProvider
import nitrosrus.ru.dogapitest.ui.network.NetworkStatus
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import java.lang.Exception
import javax.inject.Inject

@InjectViewState
class ImagePresenter(val breed: String, val subBreed: String?) : MvpPresenter<ImageView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var imageApi: ApiImage

    @Inject
    lateinit var database: IRoomFavouritesCache

    @Inject
    lateinit var rxProvider: IRxProvider

    @Inject
    lateinit var networkStatus: NetworkStatus

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    private var favouritesDatabase = mutableMapOf<String, MutableList<String>>()

    private val name = subBreed ?: breed

    val imageListPresenter = ImageListPresenter()

    inner class ImageListPresenter : IImageListPresenter {
        val imageData = mutableListOf<String>()
        override var itemClickListener: ((Int) -> Unit)? = null
        override fun getCount() = imageData.size
        override fun bind(view: ImageItemView) {
            val url = imageData[view.pos]
            view.loadImage(url)
            checkAndSetLikeDislike(url, view)
            view.setClickListener()
        }
    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        checkInternet()
        imageListPresenter.itemClickListener = { index -> logicCheckFavourite(index) }
    }

    private fun checkInternet() {
        if (networkStatus.isOnline() == true) loadData() else viewState.serverErrorInternet()
    }


    private fun loadData() {
        loadRoomDataBase()
        if (subBreed == null) loadBreeds() else loadSubBreeds()

    }

    private fun loadRoomDataBase() {

        database.getAllData().subscribeOn(rxProvider.ioThread())
            .observeOn(rxProvider.uiMainThread())
            .subscribe({ list ->
                favouritesDatabase.clear()
                list.forEach { favouritesDatabase[it.breedName] = mutableListOf() }
                list.forEach { favouritesDatabase[it.breedName]?.add(it.url) }
            }, {
                Timber.e(it)
            }).let { compositeDisposable.add(it) }

    }

    private fun loadBreeds() {
        imageApi.getImage(breed)
            .observeOn(rxProvider.uiMainThread())
            .subscribe({ list ->
                convertData(list)
            }, {
                Timber.e(it)
                checkInternet()
            }).let { compositeDisposable.add(it) }

    }

    private fun loadSubBreeds() {
        imageApi.getImage(breed, subBreed!!)
            .observeOn(rxProvider.uiMainThread())
            .subscribe({ list ->
                convertData(list)
            }, {
                Timber.e(it)
                checkInternet()
            }).let { compositeDisposable.add(it) }

    }

    private fun convertData(list: ImageBreedsList) {
        imageListPresenter.imageData.clear()
        imageListPresenter.imageData.addAll(list.message)
        updateData()
    }

    private fun logicCheckFavourite(index: Int) {
        val url = imageListPresenter.imageData[index]
        if (checkFavouritesInBase(url)) {
            favouritesDatabase[name]?.remove(url)
            updateData()
            putDisLike(url)
        } else {
            addFunction(url)
            updateData()
            putLike(url)

        }
    }

    fun addFunction(url: String) {
        try {
            favouritesDatabase[name]!!.add(url)
        } catch (e: Exception) {
            favouritesDatabase[name] = mutableListOf()
            favouritesDatabase[name]?.add(url)
        }
    }

    private fun checkFavouritesInBase(url: String): Boolean {
        favouritesDatabase[name]?.forEach {
            if (it == url) return true
        }
        return false
    }

    private fun checkAndSetLikeDislike(url: String, view: ImageItemView) {
        if (checkFavouritesInBase(url)) view.setLikeEnable() else view.setLikeDisable()
    }

    private fun putLike(url: String) {
        database.putLikeImage(name, url).observeOn(rxProvider.uiMainThread()).subscribe()
            .let { compositeDisposable.add(it) }

    }

    private fun putDisLike(url: String) {
        database.putDisLike(name, url).observeOn(rxProvider.uiMainThread()).subscribe()
            .let { compositeDisposable.add(it) }

    }


    fun awaitNetworkStatus() {
        clearRx()
        networkStatus.isOnlineObserver()
            .subscribe { online -> if (online) loadData() }
            .let { compositeDisposable.add(it) }

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


