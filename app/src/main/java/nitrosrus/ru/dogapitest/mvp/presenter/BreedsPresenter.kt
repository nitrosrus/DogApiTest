package nitrosrus.ru.dogapitest.mvp.presenter


import androidx.recyclerview.widget.SortedList
import nitrosrus.ru.dogapitest.mvp.model.breedsModel.Breeds
import nitrosrus.ru.dogapitest.mvp.model.source.ApiBreeds
import nitrosrus.ru.dogapitest.mvp.presenter.list.IBreedsListPresenter
import nitrosrus.ru.dogapitest.mvp.view.BreedsView
import nitrosrus.ru.dogapitest.mvp.view.list.BreedsItemView
import nitrosrus.ru.dogapitest.navigation.Screens
import nitrosrus.ru.dogapitest.rx.IRxProvider
import nitrosrus.ru.dogapitest.ui.network.NetworkStatus
import io.reactivex.disposables.CompositeDisposable
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import java.util.*
import javax.inject.Inject
import kotlin.Comparator
import kotlin.IndexOutOfBoundsException

@InjectViewState
class BreedsPresenter : MvpPresenter<BreedsView>() {


    @Inject
    lateinit var router: Router

    @Inject
    lateinit var apiBreeds: ApiBreeds

    @Inject
    lateinit var rxProvider: IRxProvider

    @Inject
    lateinit var networkStatus: NetworkStatus

    val breedsListPresenter = BreedsListPresenter(viewState)

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()


    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        checkInternet()
        breedsListPresenter.itemClickListener = { itemClick(it) }
    }

    private fun checkInternet() {
        if (networkStatus.isOnline() == true) loadData() else viewState.serverErrorInternet()
    }

    private fun loadData() {
        clearRx()
        apiBreeds.getBreeds()
            .subscribeOn(rxProvider.ioThread())
            .observeOn(rxProvider.uiMainThread()).map {
                convertData(it.message)
            }
            .subscribe({

            }, {
                Timber.e(it)
                checkInternet()
            }).let { compositeDisposable.add(it) }
    }

    private fun convertData(message: Map<String, List<String>>) {
        message.map { (k, v) ->
            breedsListPresenter.breedsData.add(Breeds(k, v.size))

        }
        breedsListPresenter.textChanged("")
    }


    fun awaitNetworkStatus() {
        clearRx()
        networkStatus.isOnlineObserver()
            .subscribe { online -> if (online) loadData() }.let { compositeDisposable.add(it) }

    }

    private fun itemClick(index: Int) {

            if (breedsListPresenter.sortedBreedsData[index].count != 0) {
                router.navigateTo(Screens.SubBreadsScreen(getBreedByIndex(index)))
            } else {
                router.navigateTo(Screens.ImageScreen(getBreedByIndex(index), null))
            }

    }

    private fun getBreedByIndex(index: Int) = breedsListPresenter.sortedBreedsData[index].breed

    override fun detachView(view: BreedsView?) {
        clearRx()
        super.detachView(view)
    }

    private fun clearRx() = compositeDisposable.clear()

    fun backClicked(): Boolean {
        router.exit()
        return true
    }


    class BreedsListPresenter(val viewState: BreedsView) : IBreedsListPresenter, FindTextListener {

        override var textChanged: ((String) -> Unit)? = { textChanged(it) }
        private var nameComparator = Comparator<Breeds> { a, b ->
            a.breed[0].compareTo(
                b.breed[0]
            )
        }

        private var sortedCallback: SortedList.Callback<Breeds> =
            object : SortedList.Callback<Breeds>() {

                override fun onInserted(position: Int, count: Int) {
                    viewState.notifyItemRangeInserted(position, count)
                }

                override fun onRemoved(position: Int, count: Int) {
                    viewState.notifyItemRangeRemoved(position, count)
                }

                override fun onMoved(fromPosition: Int, toPosition: Int) {
                    viewState.notifyItemMoved(fromPosition, toPosition)
                }

                override fun compare(o1: Breeds?, o2: Breeds?): Int {
                    return nameComparator.compare(o1, o2)
                }

                override fun onChanged(position: Int, count: Int) {
                    viewState.notifyItemRangeChanged(position, count)
                }

                override fun areContentsTheSame(oldItem: Breeds?, newItem: Breeds?): Boolean {
                    return oldItem == newItem
                }

                override fun areItemsTheSame(
                    item1: Breeds?, item2: Breeds?
                ): Boolean {
                    return item1?.breed == item2?.breed
                }

                override fun getChangePayload(item1: Breeds?, item2: Breeds?): Any? {
                    return super.getChangePayload(item1, item2)
                }
            }

        var breedsData = mutableListOf<Breeds>()
        var sortedBreedsData = SortedList(Breeds::class.java, sortedCallback)


        override var itemClickListener: ((Int) -> Unit)? = null
        override fun getCount() = sortedBreedsData.size()

        override fun bind(view: BreedsItemView) {
            view.setClickListener()
            view.setBreed(sortedBreedsData[view.pos].breed)
            setCountChecker(view)
        }

        private fun setCountChecker(view: BreedsItemView) {
            if (sortedBreedsData[view.pos].count != 0) {
                view.setCountVisible()
                view.setCountBreed(sortedBreedsData[view.pos].count.toString())
            } else view.setCountInvisible()
        }

        override fun getChar(index: Int): String? {
            try {
                if (index - 1 > -1) {
                    if (sortedBreedsData[index].breed[0].toLowerCase() == (sortedBreedsData[index-1].breed[0].toLowerCase())) return null
                }
                return sortedBreedsData[index].breed[0].toString()
            } catch (e: IndexOutOfBoundsException) {
                return null
            }
        }


        fun textChanged(query: String) {
            var filteredModelList: List<Breeds>? = filter(query)
            filteredModelList?.let {
                sortedBreedsData.replaceAll(it)
            }
            viewState.scrollToPosition(0)
        }

        private fun filter(query: String): List<Breeds>? {
            var filteredModelList: MutableList<Breeds> = ArrayList()
            if (query.trim().isNullOrBlank()) {
                filteredModelList.addAll(breedsData)
            } else {
                breedsData.forEach { breed ->
                    if (breed.breed.toLowerCase().contains(query.toLowerCase())) {
                        filteredModelList.add(breed)
                    }
                }
            }
            return filteredModelList
        }

    }

}

interface FindTextListener {
    var textChanged: ((String) -> Unit)?
}





