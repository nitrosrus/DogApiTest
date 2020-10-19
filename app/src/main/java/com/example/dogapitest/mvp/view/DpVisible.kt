package com.example.dogapitest.mvp.view

interface DpVisible {
    fun setActionTitle(text: String)
    fun setBreedScreensSetting(text: String)
    fun setImageBreedScreenSetting(text: String)
    fun setImageBreedScreenSetting(breed: String,subBreed: String)
    fun setFirstScreenSetting(text: String)
    fun setSubBreedScreenSetting(breed: String)
    fun setLikeBreedScreenSetting()
    fun setLikeImageScreenSetting(breed: String)

}