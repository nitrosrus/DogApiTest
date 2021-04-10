package nitrosrus.ru.dogapitest.mvp.presenter


import nitrosrus.ru.dogapitest.mvp.model.breedsModel.SubBreedsList
import nitrosrus.ru.dogapitest.mvp.model.source.ApiBreeds
import nitrosrus.ru.dogapitest.mvp.presenter.list.ISubBreedListPresenter
import nitrosrus.ru.dogapitest.mvp.view.SubBreedsView
import nitrosrus.ru.dogapitest.mvp.view.list.SubBreedsItemView
import nitrosrus.ru.dogapitest.navigation.Screens
import nitrosrus.ru.dogapitest.rx.IRxProvider
import nitrosrus.ru.dogapitest.ui.network.NetworkStatus
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class SubBreedsPresenter(val breeds: String) :
    MvpPresenter<SubBreedsView>() {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var apiBreeds: ApiBreeds

    @Inject
    lateinit var rxProvider: IRxProvider

    @Inject
    lateinit var networkStatus: NetworkStatus

    val subBreedsListPresenter = SubBreedsListPresenter()

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    class SubBreedsListPresenter() : ISubBreedListPresenter {
        val subBreedsData = mutableListOf<String>()
        override var itemClickListener: ((Int) -> Unit)? = null
        override fun getCount() = subBreedsData.size
        override fun bind(view: SubBreedsItemView) {
            view.setClickListener()
            view.setBreed(subBreedsData[view.pos])
        }

    }

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        checkInternet()
        subBreedsListPresenter.itemClickListener = { index ->
            itemClick(index)
        }
    }

    private fun checkInternet() {
        if (networkStatus.isOnline() == true) loadData() else viewState.serverErrorInternet()
    }

    fun loadData() {
        clearRx()
        compositeDisposable.add(
            apiBreeds.getSubBreeds(breeds)
                .subscribeOn(rxProvider.ioThread())
                .observeOn(rxProvider.uiMainThread())
                .subscribe({ subBreeds ->
                    convertData(subBreeds)
                }, {
                    Timber.e(it)
                    checkInternet()
                })
        )
    }

    private fun convertData(subBreeds: SubBreedsList) {
        subBreedsListPresenter.subBreedsData.clear()
        subBreedsListPresenter.subBreedsData.addAll(subBreeds.message)
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
        router.navigateTo(Screens.ImageScreen(breeds, getBreedByIndex(index)))
    }

    private fun getBreedByIndex(index: Int) = subBreedsListPresenter.subBreedsData[index]

    override fun detachView(view: SubBreedsView?) {
        clearRx()
        super.detachView(view)
    }

    private fun clearRx() = compositeDisposable.clear()

    fun backClicked(): Boolean {
        router.exit()
        return true
    }

}




