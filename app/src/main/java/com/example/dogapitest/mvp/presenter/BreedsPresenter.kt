package com.example.dogapitest.mvp.presenter


import com.example.dogapitest.mvp.model.repo.DogApiBreeds
import com.example.dogapitest.mvp.presenter.list.IBreedsListPresener
import com.example.dogapitest.mvp.view.BreedsView
import com.example.dogapitest.mvp.view.list.BreedsItemView
import com.example.dogapitest.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class BreedsPresenter(val mainThreadScheduler: Scheduler) : MvpPresenter<BreedsView>() {



     class BreedsListPresenter : IBreedsListPresener {
        val breeds = mutableMapOf<String, List<String>>()
        override var itemClickListener: ((BreedsItemView) -> Unit)? = null
        override fun getCount() = breeds.size
        override fun bindView(view: BreedsItemView) {
            listBreeds(view)
            view.countVisible(breeds.values.elementAt(view.pos).isEmpty())
        }

        fun listBreeds(view: BreedsItemView) {
            view.setBreed(breeds.keys.elementAt(view.pos))
            view.setCountBreed(breeds.values.elementAt(view.pos).size.toString())

        }

    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var apiBreeds: DogApiBreeds

    val breedsListPresenter = BreedsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        breedsListPresenter.itemClickListener = { view ->
            if (breedsListPresenter.breeds[view.getBreads()]?.size == 0) {
                router.navigateTo(Screens.ImageScreen(ArrayList<String>().apply {
                    add(view.getBreads())
                }))

            } else {
                router.navigateTo(Screens.SubBreadsScreen(ArrayList<String>().apply {
                    add(view.getBreads())
                }))
            }


        }


    }


    fun loadData() {

        apiBreeds.getBreeds().observeOn(mainThreadScheduler).subscribe({ breeds ->
            breedsListPresenter.breeds.clear()
            breedsListPresenter.breeds.putAll(breeds.message)
            viewState.updateList()
        }, {
            viewState.serverErrorInternet()
            Timber.e(it)
        })

    }


    fun backClicked(): Boolean {
        router.exit()
        return true
    }

}






