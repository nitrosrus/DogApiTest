package com.example.dogapitest.mvp.view.list

interface FavouritesImageItemView {
    var pos: Int
    fun loadImage(url: String)
    fun setLikeEnable()
    fun setClickListener()
}