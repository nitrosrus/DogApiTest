package com.example.dogapitest.mvp.view.list

interface SubBreedsItemView {
    var pos: Int
    fun setBreed(text: String)
    fun setCountBreed(text: String)
    fun getBreads(): String
    fun setCountVisible()
    fun setCountInvisible()
    fun setClickListener()




}