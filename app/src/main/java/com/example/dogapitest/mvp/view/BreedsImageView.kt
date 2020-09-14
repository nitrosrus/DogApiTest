package com.example.dogapitest.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BreedsImageView : MvpView {
    fun init()
    fun updateList()

    fun setLikeStasus()
    fun loadImage(url: String)
}