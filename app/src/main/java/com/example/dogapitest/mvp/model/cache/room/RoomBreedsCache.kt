package com.example.dogapitest.mvp.model.cache.room

import com.example.dogapitest.mvp.model.cache.IBreedsCache
import com.example.dogapitest.mvp.model.entity.room.db.Database

import com.example.dogapitest.mvp.model.entity.room.db.RoomCacheLike
import com.example.dogapitest.mvp.model.entity.room.db.RoomCachedBreeds
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomBreedsCache(val database: Database) : IBreedsCache {

    override fun getLikeImages(breeds: String) = Single.fromCallable {
        return@fromCallable database.breedsDao.getAllImage().map { tut ->
            tut.url.toString()
        }
    }.subscribeOn(Schedulers.io())


    override fun putLikeImage(breed: String, url: String) = Completable.fromAction {

        database.breedsDao.insert(RoomCachedBreeds(breed), RoomCacheLike(breed,url))

//        database.breedsDao.insert(RoomCachedBreeds(breed))
//        database.breedsDao.insert(RoomCacheLike(breed,url))


    }.subscribeOn(Schedulers.io())


}