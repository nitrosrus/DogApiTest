package com.example.dogapitest.mvp.view.list

interface LikeImageItemView {
    var pos: Int
    fun setlike(boolean: Boolean)
    fun loadImage(url: String)
    fun setBreed(breeds: String)
    fun setCountBreed(countBreeds: String)
    fun getBreads(): String
}