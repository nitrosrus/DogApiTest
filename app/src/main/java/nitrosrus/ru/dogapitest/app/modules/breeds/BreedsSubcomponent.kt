package nitrosrus.ru.dogapitest.app.modules.breeds


import nitrosrus.ru.dogapitest.mvp.presenter.BreedsPresenter
import nitrosrus.ru.dogapitest.mvp.presenter.FavouritesBreedsPresenter
import nitrosrus.ru.dogapitest.mvp.presenter.SubBreedsPresenter
import nitrosrus.ru.dogapitest.ui.adapter.BreedsRVAdapter
import nitrosrus.ru.dogapitest.ui.adapter.FavouritesBreedsRVAdapter
import nitrosrus.ru.dogapitest.ui.adapter.SubBreedsRVAdapter
import nitrosrus.ru.dogapitest.ui.fragment.BreedsFragment
import nitrosrus.ru.dogapitest.ui.fragment.FavouritesBreedsFragment
import nitrosrus.ru.dogapitest.ui.fragment.SubBreedsFragment
import dagger.Subcomponent

@BreedsScope
@Subcomponent(
    modules = [BreedsModule::class
    ]
)

interface BreedsSubcomponent {

    fun inject(breedsFragment: BreedsFragment)
    fun inject(breedsPresenter: BreedsPresenter)
    fun inject(breedsRVAdapter: BreedsRVAdapter)
    fun inject(subBreedsRVAdapter: SubBreedsRVAdapter)
    fun inject(subBreedsFragment: SubBreedsFragment)
    fun inject(subBreedsPresenter: SubBreedsPresenter)
    fun inject(favouritesBreedsPresenter: FavouritesBreedsPresenter)
    fun inject(favouritesBreedsFragment: FavouritesBreedsFragment)
    fun inject(favouritesBreedsRVAdapter: FavouritesBreedsRVAdapter)



}