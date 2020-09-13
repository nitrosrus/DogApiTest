package com.example.dogapitest.mvp.view.list

interface ImageItemView {
    var pos: Int
    fun setlike(text: String)
    fun loadImage(url: String)
}