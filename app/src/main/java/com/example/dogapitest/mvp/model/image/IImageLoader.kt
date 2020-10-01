package com.example.dogapitest.mvp.model.image


import com.example.dogapitest.mvp.model.cache.image.IImageCache

interface IImageLoader<T> {
    val cache: IImageCache
    fun loadInto(url: String, container: T,preload: T)

}