package com.example.dogapitest.mvp.model.cache

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IBreedsCache {

    fun getLikeImages(breeds: String): Single<List<String>>                //возвращает список url
    fun getAllLike(): Single<Map<String, List<String>>>                   //возвращает карту порода и список url
    fun putLikeImage(breed: String, url: String): Completable             // добавляет в базу понравившуюся фото
    fun putDisLike(breed: String,url: String): Completable                // убирает из базы понрвившееся фото


}