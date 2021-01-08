package com.example.dogapitest.mvp.model.cache

import com.example.dogapitest.mvp.model.entity.room.db.RoomCacheLike
import io.reactivex.Completable
import io.reactivex.Single

interface IBreedsCache {


    fun getAllData(): Single<List<RoomCacheLike>>              //возвращает карту порода и список url

    fun putLikeImage(breed: String, url: String): Completable             // добавляет в базу понравившуюся фото

    fun putDisLike(breed: String,url: String): Completable                // убирает из базы понрвившееся фото


}