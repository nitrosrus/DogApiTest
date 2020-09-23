package com.example.dogapitest.di.app.modules.breeds

import com.example.dogapitest.mvp.presenter.BreedsPresenter
import com.example.dogapitest.mvp.presenter.LikeBreedsPresenter
import com.example.dogapitest.mvp.presenter.SubBreedsPresenter
import com.example.dogapitest.ui.adapter.BreedsRVAdapter
import com.example.dogapitest.ui.adapter.SubBreedsRVAdapter
import com.example.dogapitest.ui.fragment.BreedsFragment
import com.example.dogapitest.ui.fragment.LikeBreedsFragment
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
    fun inject(likeBreedsPresenter: LikeBreedsPresenter)
    fun inject(likeBreedsFragment: LikeBreedsFragment)
    //fun inject(adapter: BreedsRVAdapter)


}