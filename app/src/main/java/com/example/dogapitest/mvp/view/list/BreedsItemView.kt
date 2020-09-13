package com.example.dogapitest.mvp.view.list

interface BreedsItemView {
    var pos: Int
    fun setBreed(text: String)
    fun setCountBreed(text: String)
    fun getBreads(): String


}