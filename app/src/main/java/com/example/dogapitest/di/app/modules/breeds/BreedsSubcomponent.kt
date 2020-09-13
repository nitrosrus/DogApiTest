package com.example.dogapitest.di.app.modules.breeds

import com.example.dogapitest.di.app.modules.image.ImModule
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

@BreedsScope
@Subcomponent(
    modules = [BreedsModule::class]
)

interface BreedsSubcomponent {

    fun inject(breedsFragment: BreedsFragment)
    fun inject(breedsPresenter: BreedsPresenter)
    fun inject(adapter: BreedsRVAdapter)
    fun inject(subBreedsRVAdapter: SubBreedsRVAdapter)
    fun inject(subBreedsFragment: SubBreedsFragment)
    fun inject(subBreedsPresenter: SubBreedsPresenter)


}