package com.example.dogapitest.mvp.model.cache.room


import android.renderscript.Sampler
import com.example.dogapitest.mvp.model.cache.IBreedsCache
import com.example.dogapitest.mvp.model.entity.room.db.Database
import com.example.dogapitest.mvp.model.entity.room.db.RoomCacheLike
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomBreedsCache(val database: Database) : IBreedsCache {

    val map = mutableMapOf<String, MutableList<String>>()

    override fun getAllLike(): Single<Map<String, List<String>>> {
        return Single.fromCallable {
            converterToMap(database.breedsDao.getAllLike())
        }.subscribeOn(Schedulers.io())
    }

    private fun converterToMap(listDb: List<RoomCacheLike>): Map<String, List<String>> {
        map.clear()
        listDb.forEach { map[it.breedName] = mutableListOf() }
        listDb.forEach { map[it.breedName]?.add(it.url) }
        return map
    }

    override fun getLikeImages(breeds: String) = Single.fromCallable {
        return@fromCallable database.breedsDao.getAllLike().map { tut ->
            tut.url
        }
    }.subscribeOn(Schedulers.io())


    override fun putLikeImage(breed: String, url: String) = Completable.fromAction {

        database.breedsDao.insert(RoomCacheLike(breed, url))

    }.subscribeOn(Schedulers.io())

    override fun putDisLike(breed: String, url: String) = Completable.fromAction {
        database.breedsDao.delete(RoomCacheLike(breed, url))
    }.subscribeOn(Schedulers.io())


}


