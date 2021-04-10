package nitrosrus.ru.dogapitest.mvp.presenter

import nitrosrus.ru.dogapitest.mvp.view.MainView
import nitrosrus.ru.dogapitest.navigation.Screens
import moxy.InjectViewState
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject


@InjectViewState
class MainPresenter() : MvpPresenter<MainView>() {


    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        router.replaceScreen(Screens.BreedsScreen())

    }




    fun backClicked() {
        router.exit()
    }


    fun btnList() {
        router.navigateTo(Screens.BreedsScreen())
    }

    fun btnFavorites() {
        router.navigateTo(Screens.FavouritesBreedsScreen())

    }


}