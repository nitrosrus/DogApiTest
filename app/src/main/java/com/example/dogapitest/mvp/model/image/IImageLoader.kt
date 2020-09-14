package com.example.dogapitest.mvp.model.image

import com.example.dogapitest.mvp.model.cache.image.IImageCache

interface IImageLoader<T> {
    val cache: IImageCache
    fun loadInto(breed: String, url: String, container: T)

}