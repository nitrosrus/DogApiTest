package nitrosrus.ru.dogapitest.app.modules.image

import dagger.Subcomponent
import nitrosrus.ru.dogapitest.mvp.presenter.FavouritesImagePresenter
import nitrosrus.ru.dogapitest.mvp.presenter.ImagePresenter
import nitrosrus.ru.dogapitest.ui.adapter.FavouritesImageRVAdapter
import nitrosrus.ru.dogapitest.ui.adapter.ImageRVAdapter
import nitrosrus.ru.dogapitest.ui.fragment.FavouritesImageFragment
import nitrosrus.ru.dogapitest.ui.fragment.ImageFragment


@ImageScope
@Subcomponent(
    modules = [ImModule::class]
)
interface ImageSubcomponent {


    fun inject(imagePresenter: ImagePresenter)
    fun inject(imageRVAdapter: ImageRVAdapter)
    fun inject(imageFragment: ImageFragment)
    fun inject(favouritesImageFragment: FavouritesImageFragment)
    fun inject(favouritesImagePresenter: FavouritesImagePresenter)
    fun inject(favouritesImageRVAdapter: FavouritesImageRVAdapter)

}