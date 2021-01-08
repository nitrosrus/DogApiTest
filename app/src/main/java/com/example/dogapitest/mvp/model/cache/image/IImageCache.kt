package com.example.dogapitest.mvp.model.cache.image

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

interface IImageCache {

    fun contains(url: String): Single<Boolean>
    fun getBytes(url: String): Maybe<ByteArray?>
    fun saveImage(url: String, bytes: ByteArray): Completable
    fun clear(): Completable
}