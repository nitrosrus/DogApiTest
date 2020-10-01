package com.example.dogapitest.di.app.modules.image

import com.example.dogapitest.mvp.presenter.ImagePresenter
import com.example.dogapitest.mvp.presenter.LikeImagePresenter
import com.example.dogapitest.ui.adapter.ImageRVAdapter
import com.example.dogapitest.ui.fragment.ImageFragment
import com.example.dogapitest.ui.fragment.LikeImageFragment
import dagger.Subcomponent


@ImageScope
@Subcomponent(
    modules = [ImModule::class]
)
interface ImageSubcomponent {


    fun inject(imagePresenter: ImagePresenter)
    fun inject(imageRVAdapter: ImageRVAdapter)
    fun inject(imageFragment: ImageFragment)
    fun inject(likeImageFragment: LikeImageFragment)
    fun inject(likeImagePresenter: LikeImagePresenter)

}