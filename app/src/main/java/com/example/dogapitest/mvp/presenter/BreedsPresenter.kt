package com.example.dogapitest.mvp.presenter


import com.example.dogapitest.mvp.model.breedsModel.BreedsList
import com.example.dogapitest.mvp.model.repo.DogApiBreeds
import com.example.dogapitest.mvp.presenter.list.IBreedsListPresenter
import com.example.dogapitest.mvp.view.BreedsView
import com.example.dogapitest.mvp.view.list.BreedsItemView
import com.example.dogapitest.navigation.Screens
import com.example.dogapitest.rx.IRxProvider
import com.example.dogapitest.ui.network.NetworkStatus
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class BreedsPresenter() : MvpPresenter<BreedsView>() {


    @Inject
    lateinit var router: Router

    @Inject
    lateinit var apiBreeds: DogApiBreeds

    @Inject
    lateinit var rxProvider: IRxProvider

    @Inject
    lateinit var networkStatus: NetworkStatus

    val breedsListPresenter = BreedsListPresenter()

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    class BreedsListPresenter() : IBreedsListPresenter {

        var breedsData = mutableMapOf<String, Int>()
        override var itemClickListener: ((Int) -> Unit)? = null
        override fun getCount() = breedsData.keys.size
        override fun bind(view: BreedsItemView) {
            view.setClickListener()
            view.setBreed(breedsData.keys.elementAt(view.pos))
            setCountChecker(view)
        }

        private fun setCountChecker(view: BreedsItemView) {
            if (breedsData.values.elementAt(view.pos) != 0) {
                view.setCountVisible()
                view.setCountBreed(breedsData.values.elementAt(view.pos).toString())
            } else view.setCountInvisible()

        }

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        checkInternet()
        breedsListPresenter.itemClickListener = { index ->
            itemClick(index)
        }

    }

    private fun checkInternet() {
        if (networkStatus.isOnline() == true) loadData() else viewState.serverErrorInternet()
    }

    private fun loadData() {
        clearRx()
        compositeDisposable.add(
            apiBreeds.getBreeds()
                .subscribeOn(rxProvider.ioThread())
                .observeOn(rxProvider.uiMainThread())
                .subscribe({ breeds ->
                    convertData(breeds)
                }, {
                    Timber.e(it)
                    checkInternet()
                })
        )
    }

    private fun convertData(breeds: BreedsList) {
        breedsListPresenter.breedsData.clear()
        breeds.message.forEach { entryMap ->
            breedsListPresenter.breedsData[entryMap.key] = entryMap.value.size
        }

        updateData()

    }

    private fun updateData() = viewState.updateRVAdapter()

    fun awaitNetworkStatus() {
        clearRx()
        compositeDisposable.add(
            networkStatus.isOnlineObserver()
                .subscribe { online -> if (online) loadData() }
        )
    }

    private fun itemClick(index: Int) {
        if (breedsListPresenter.breedsData.values.elementAt(index) != 0) {
            router.navigateTo(Screens.SubBreadsScreen(getBreedByIndex(index)))
        } else {
            router.navigateTo(Screens.ImageScreen(getBreedByIndex(index), null))
        }

    }

    private fun getBreedByIndex(index: Int) = breedsListPresenter.breedsData.keys.elementAt(index)

    override fun detachView(view: BreedsView?) {
        clearRx()
        super.detachView(view)
    }

    private fun clearRx() = compositeDisposable.clear()

    fun backClicked(): Boolean {
        router.exit()
        return true
    }

}






