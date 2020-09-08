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
import ru.terrakok.cicerone.Screen
import javax.inject.Inject

@InjectViewState
class BreedsPresenter(val mainThreadScheduler: Scheduler) : MvpPresenter<BreedsView>() {


    class BreedsListPresenter : IBreedsListPresener {
         val breeds = mutableMapOf<String, List<String>>()
        override var itemClickListener: ((BreedsItemView) -> Unit)? = null
        override fun getCount() = breeds.size
        override fun bindView(view: BreedsItemView) {
            listBreeds(view)
        }

        fun listBreeds(view: BreedsItemView) {

            view.setBreed(breeds.keys.elementAt(view.pos))
            if (breeds.values.size > 0) view.setCountBreed(breeds.values.elementAt(view.pos).size.toString())

        }

        fun listSubBreads(view: BreedsItemView, name: String) {
            breeds.values.elementAt(view.pos).forEach { tut -> view.setBreed(tut) }

        }

        fun clickNameBreeds(view: BreedsItemView) {
            if (breeds[view.getBreads()] != null) {
                breeds[view.getBreads()]?.forEach { subbred ->
                    view.setBreed(subbred)
                    println(subbred)
                }
            }
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
           // val breed=breedsListPresenter.clickNameBreeds(view)
            println(view.getBreads())
            breedsListPresenter.breeds[view.getBreads()]?.size.let {
                if (it != 0)  router.replaceScreen(breedsListPresenter.breeds[view.getBreads()]?.let {
                    Screens.SubBreadsScreen(it)
                })

            }




        }
    }


    fun loadData() {

        apiBreeds.getBreeds().observeOn(mainThreadScheduler).subscribe({ breeds ->
            breedsListPresenter.breeds.clear()
            breedsListPresenter.breeds.putAll(breeds.message)
            viewState.updateList()
        }, {
            println("qwerty " + it)
        })

    }


    fun backClicked(): Boolean {
        router.exit()
        return true
    }

}






