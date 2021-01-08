package com.example.dogapitest.di.app.modules.breeds


import com.example.dogapitest.mvp.presenter.BreedsPresenter
import com.example.dogapitest.mvp.presenter.FavouritesBreedsPresenter
import com.example.dogapitest.mvp.presenter.SubBreedsPresenter
import com.example.dogapitest.ui.adapter.BreedsRVAdapter
import com.example.dogapitest.ui.adapter.FavouritesBreedsRVAdapter
import com.example.dogapitest.ui.adapter.SubBreedsRVAdapter
import com.example.dogapitest.ui.fragment.BreedsFragment
import com.example.dogapitest.ui.fragment.FavouritesBreedsFragment
import com.example.dogapitest.ui.fragment.SubBreedsFragment
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