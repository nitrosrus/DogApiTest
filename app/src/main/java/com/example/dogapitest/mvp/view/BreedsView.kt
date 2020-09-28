package com.example.dogapitest.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface BreedsView : MvpView {
    fun init()
    fun updateList()
    fun settitle(text:String)
     fun serverErrorInternet()
}