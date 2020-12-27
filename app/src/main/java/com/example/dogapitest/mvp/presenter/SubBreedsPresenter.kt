package com.example.dogapitest.mvp.presenter


import com.example.dogapitest.mvp.model.repo.DogApiBreeds
import com.example.dogapitest.mvp.presenter.list.IBreedsListPresenter
import com.example.dogapitest.mvp.presenter.list.ISubBreedListPresenter
import com.example.dogapitest.mvp.view.SubBreedsView
import com.example.dogapitest.mvp.view.list.BreedsItemView
import com.example.dogapitest.mvp.view.list.SubBreedsItemView
import com.example.dogapitest.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class SubBreedsPresenter(val mainThreadScheduler: Scheduler, val breeds: String) :
    MvpPresenter<SubBreedsView>() {


    class SubBreedsListPresenter() : ISubBreedListPresenter {
        val subBreeds = mutableListOf<String>()
        override var itemClickListener: ((SubBreedsItemView) -> Unit)? = null
        override fun getCount() = subBreeds.size
        override fun bindView(view: SubBreedsItemView) {
            val breed = subBreeds[view.pos]
            view.setBreed(breed)
            visibleChecker(view)
        }

        private fun visibleChecker(view: SubBreedsItemView) {
            if (subBreeds.elementAt(view.pos).isEmpty()) {
                view.setCountInvisible()
            } else {
                view.setCountVisible()
            }
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
            router.navigateTo(Screens.ImageScreen(breeds, view.getBreads()))
        }

    }


    fun loadData() {
        apiBreeds.getSubBreeds(breeds).observeOn(mainThreadScheduler).subscribe({ breedList ->
            subBreedsListPresenter.subBreeds.clear()
            subBreedsListPresenter.subBreeds.addAll(breedList.message)
            viewState.updateRVAdapter()
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




