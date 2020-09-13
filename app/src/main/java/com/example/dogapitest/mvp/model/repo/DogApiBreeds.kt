package com.example.dogapitest.mvp.model.repo

import com.example.dogapitest.mvp.model.api.IDataSource
import com.example.dogapitest.mvp.model.network.NetworkStatus
import io.reactivex.rxjava3.schedulers.Schedulers

class DogApiBreeds(
    val api: IDataSource,
    val networkStatus: NetworkStatus

) {
//    fun getBreeds() = networkStatus.isOnlineSingle().flatMap {isOnLine  ->
//        if (isOnLine) {
//            api.getBreeds().flatMap { breeds ->
//                 cache.putBreeds(breeds).toSingleDefault(breeds)
//            }
//        } else {
//            cache.getBreeds()
//        }
//    }.subscribeOn(Schedulers.io())

    fun getBreeds() = api.getBreeds().subscribeOn(Schedulers.io())
    fun getSubBreeds(breed: String) = api.getSubBreeds(breed).subscribeOn(Schedulers.io())
}