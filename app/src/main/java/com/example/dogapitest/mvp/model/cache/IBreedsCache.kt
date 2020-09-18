package com.example.dogapitest.mvp.model.cache

import com.example.dogapitest.mvp.model.breedsModel.ImageBreedsList
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IBreedsCache {

    fun getLikeImages(breeds: String): Single<List<String>>                  //возвращает список url
   // fun getLikeBreeds(): Single<Map<String, String>>                   //возвращает карту порода и список url
    fun putLikeImage(breed: String, url: String): Completable                // добавляет в базу понравившуюся фото
   // fun putDisLike(url: String): Completable                                 // убирает из базы понрвившееся фото


}