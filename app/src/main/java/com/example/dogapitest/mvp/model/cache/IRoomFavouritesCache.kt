package com.example.dogapitest.mvp.model.cache

import com.example.dogapitest.mvp.model.entity.RoomFavourites
import io.reactivex.Completable
import io.reactivex.Single

interface IRoomFavouritesCache {


    fun getAllData(): Single<List<RoomFavourites>>              //возвращает карту порода и список url

    fun putLikeImage(breed: String, url: String): Completable             // добавляет в базу понравившуюся фото

    fun putDisLike(breed: String,url: String): Completable                // убирает из базы понрвившееся фото


}