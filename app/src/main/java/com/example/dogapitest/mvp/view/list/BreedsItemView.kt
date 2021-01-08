package com.example.dogapitest.mvp.view.list

interface BreedsItemView {
    var pos: Int
    fun setBreed(breed: String)
    fun setCountBreed(countBreed: String)
    fun setClickListener()
    fun setCountVisible()
    fun setCountInvisible()

}