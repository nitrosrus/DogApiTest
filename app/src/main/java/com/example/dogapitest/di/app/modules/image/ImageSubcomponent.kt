package com.example.dogapitest.di.app.modules.image

import com.example.dogapitest.mvp.presenter.*
import com.example.dogapitest.ui.adapter.*
import com.example.dogapitest.ui.fragment.*
import dagger.Subcomponent


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