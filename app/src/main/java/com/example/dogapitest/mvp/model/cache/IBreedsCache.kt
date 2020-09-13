package com.example.dogapitest.mvp.model.cache

import io.reactivex.rxjava3.core.Single

interface IBreedsCache {

    fun getBreed(breeds: String): Single<String>
//    fun putBreed(breeds: Breeds): Completable
//
//
//    fun getAllLikeBreed(like:Int)
//    fun putLikeBreed():Completable



}