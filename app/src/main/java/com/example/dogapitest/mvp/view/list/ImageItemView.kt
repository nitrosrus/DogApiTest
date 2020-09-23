package com.example.dogapitest.mvp.view.list

interface ImageItemView {
    var pos: Int
    fun setlike(boolean: Boolean)
    fun loadImage(url: String)
}