package com.example.dogapitest.mvp.model.cache.room

import com.example.dogapitest.mvp.model.cache.IBreedsCache
import com.example.dogapitest.mvp.model.entity.room.db.Database
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomBreedsCache(val database: Database) : IBreedsCache {
    override fun getBreed(breeds: String)= Single.fromCallable {
        return@fromCallable database.breedsDao.FindByBreeds(breeds)?.run {subBreeds
        }
            ?: throw RuntimeException("No breeds in cache")

    }.subscribeOn(Schedulers.io())

//    override fun putBreed(breeds: Breeds): Completable {
//
//    }

//    override fun getAllLikeBreed(like: Int) {
//
//    }
//
//    override fun putLikeBreed(): Completable {
//
//    }


}