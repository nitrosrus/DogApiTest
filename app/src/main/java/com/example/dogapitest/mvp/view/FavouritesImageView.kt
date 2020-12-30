package com.example.dogapitest.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FavouritesImageView : MvpView {
    fun init()
    fun updateRVAdapter()
    fun serverErrorInternet()

}