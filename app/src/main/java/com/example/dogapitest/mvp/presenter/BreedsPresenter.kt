package com.example.dogapitest.mvp.presenter


import com.example.dogapitest.mvp.model.breedsModel.BreedsList
import com.example.dogapitest.mvp.model.repo.DogApiBreeds
import com.example.dogapitest.mvp.presenter.list.IBreedsListPresenter
import com.example.dogapitest.mvp.view.BreedsView
import com.example.dogapitest.mvp.view.list.BreedsItemView
import com.example.dogapitest.navigation.Screens
import io.reactivex.disposables.Disposable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class BreedsPresenter(private val mainThreadScheduler: Scheduler) : MvpPresenter<BreedsView>() {


    @Inject
    lateinit var router: Router

    @Inject
    lateinit var apiBreeds: DogApiBreeds

    val breedsListPresenter = BreedsListPresenter()

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
        loadData()
        breedsListPresenter.itemClickListener = { index ->
            itemClick(index)
        }
    }


    private fun loadData() {
        apiBreeds.getBreeds()
            .subscribeOn(Schedulers.io())
            .observeOn(mainThreadScheduler)
            .subscribe({ breeds ->
                convertData(breeds)
            }, {
                Timber.e(it)
                viewState.serverErrorInternet()

            })

    }

    private fun convertData(breeds: BreedsList) {
        breedsListPresenter.breedsData.clear()
        breeds.message.forEach { entry ->
            breedsListPresenter.breedsData[entry.key] = entry.value.size
        }
        updateData()
    }

    private fun itemClick(index: Int) {
        if (breedsListPresenter.breedsData.values.elementAt(index) != 0) {
            router.navigateTo(Screens.SubBreadsScreen(getBreedByIndex(index)))
        } else {
            router.navigateTo(Screens.ImageScreen(getBreedByIndex(index), null))
        }
    }

    private fun getBreedByIndex(index: Int) = breedsListPresenter.breedsData.keys.elementAt(index)

    private fun updateData() = viewState.updateRVAdapter()


    fun backClicked(): Boolean {
        router.exit()
        return true
    }

}






