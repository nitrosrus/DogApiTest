package com.example.dogapitest.mvp.presenter


import com.example.dogapitest.mvp.model.repo.DogApiBreeds
import com.example.dogapitest.mvp.presenter.list.IBreedsListPresener
import com.example.dogapitest.mvp.view.BreedsView
import com.example.dogapitest.mvp.view.SubBreedsView
import com.example.dogapitest.mvp.view.list.BreedsItemView
import com.example.dogapitest.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class SubBreedsPresenter(val mainThreadScheduler: Scheduler, val subBreeds: ArrayList<String>) :
    MvpPresenter<SubBreedsView>() {


     class SubBreedsListPresenter : IBreedsListPresener {
        val breeds = mutableListOf<String>()
        override var itemClickListener: ((BreedsItemView) -> Unit)? = null
        override fun getCount() = breeds.size
        override fun bindView(view: BreedsItemView) {
            val breed = breeds[view.pos]
            view.setBreed(breed)
            view.countVisible(breeds.elementAt(view.pos).isEmpty())
        }
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var apiBreeds: DogApiBreeds


    val subBreedsListPresenter = SubBreedsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        subBreedsListPresenter.itemClickListener = { view ->
            router.navigateTo(Screens.ImageScreen(subBreeds.apply {
                add(view.getBreads())
            }))
        }

    }


    fun loadData() {

        apiBreeds.getSubBreeds(subBreeds[0]).observeOn(mainThreadScheduler).subscribe({ breedList ->
            subBreedsListPresenter.breeds.clear()
            subBreedsListPresenter.breeds.addAll(breedList.message)
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




