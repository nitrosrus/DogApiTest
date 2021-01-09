package com.example.dogapitest.mvp.view

interface DpVisible {
    fun setBreedScreensSetting(text: String)
    fun setImageBreedScreenSetting(text: String)
    fun setFirstScreenSetting()
    fun setSubBreedScreenSetting(breed: String)
    fun setLikeBreedScreenSetting()
    fun setFavouritesImageScreenSetting(breed: String)

}