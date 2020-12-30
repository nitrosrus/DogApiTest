package com.example.dogapitest.mvp.presenter


import com.example.dogapitest.mvp.model.cache.IBreedsCache
import com.example.dogapitest.mvp.presenter.list.IFavouritesBreedsListPresenter
import com.example.dogapitest.mvp.view.FavouritesBreedsView
import com.example.dogapitest.mvp.view.list.FavouritesBreedsItemView
import com.example.dogapitest.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import timber.log.Timber
import javax.inject.Inject

@InjectViewState
class FavouritesBreedsPresenter(val mainThreadScheduler: Scheduler) : MvpPresenter<FavouritesBreedsView>() {


    inner class BreedsListPresenter : IFavouritesBreedsListPresenter {
        override var itemClick: ((String) -> Unit)?=null
        val breeds = mutableMapOf<String, List<String>>()
        override var itemClickListener: ((FavouritesBreedsItemView) -> Unit)? = null
        override fun getCount() = breeds.size
        override fun bindView(view: FavouritesBreedsItemView) {
            listBreeds(view)
        }

        fun listBreeds(view: FavouritesBreedsItemView) {
            view.setBreed(breeds.keys.elementAt(view.pos))
            if (breeds.values.size > 0) view.setCountBreed(breeds.values.elementAt(view.pos).size.toString())
        }

    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var database: IBreedsCache

    val breedsListPresenter = BreedsListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()
        breedsListPresenter.itemClickListener = { view ->
            router.navigateTo(Screens.LikeImageScreen(view.getBreads()))

        }
    }


    fun loadData() {

        database.getAllLike().observeOn(mainThreadScheduler).subscribe({ breeds ->
            breedsListPresenter.breeds.clear()
            breedsListPresenter.breeds.putAll(breeds)
            viewState.updateRVAdapter()
        }, {
            Timber.e(it)
        })

    }


    fun backClicked(): Boolean {
        router.exit()
        return true
    }

}






