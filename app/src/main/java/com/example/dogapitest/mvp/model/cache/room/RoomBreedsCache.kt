package com.example.dogapitest.mvp.model.cache.room


import com.example.dogapitest.mvp.model.cache.IBreedsCache
import com.example.dogapitest.mvp.model.entity.room.db.Database
import com.example.dogapitest.mvp.model.entity.room.db.RoomCacheLike
import com.example.dogapitest.rx.IRxProvider
import io.reactivex.Completable
import io.reactivex.Single

class RoomBreedsCache(val database: Database, var rxProvider: IRxProvider) : IBreedsCache {


    override fun getAllData(): Single<List<RoomCacheLike>> {
        return database.breedsDao.getAllLike()
    }

    override fun putLikeImage(breed: String, url: String) = Completable.fromAction {

        database.breedsDao.insert(RoomCacheLike(breedName = breed, url = url))

    }.subscribeOn(rxProvider.ioThread())

    override fun putDisLike(breed: String, url: String) = Completable.fromAction {
        database.breedsDao.delete(RoomCacheLike(breedName = breed, url = url))
    }.subscribeOn(rxProvider.ioThread())


}


