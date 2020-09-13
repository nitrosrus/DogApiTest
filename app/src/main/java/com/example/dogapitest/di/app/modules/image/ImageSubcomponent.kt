package com.example.dogapitest.di.app.modules.image

import com.example.dogapitest.di.app.modules.breeds.BreedsModule
import com.example.dogapitest.mvp.presenter.BreedsPresenter
import com.example.dogapitest.mvp.presenter.ImagePresenter
import com.example.dogapitest.mvp.presenter.SubBreedsPresenter
import com.example.dogapitest.ui.adapter.BreedsRVAdapter
import com.example.dogapitest.ui.adapter.ImageRVAdapter
import com.example.dogapitest.ui.adapter.SubBreedsRVAdapter
import com.example.dogapitest.ui.fragment.BreedsFragment
import com.example.dogapitest.ui.fragment.ImageFragment
import com.example.dogapitest.ui.fragment.SubBreedsFragment
import dagger.Subcomponent


@ImageScope
@Subcomponent(
    modules = [ImModule::class]
)
interface ImageSubcomponent {


    fun inject(imagePresenter: ImagePresenter)
    fun inject(imageRVAdapter: ImageRVAdapter)
    fun inject(imageFragment: ImageFragment)
}