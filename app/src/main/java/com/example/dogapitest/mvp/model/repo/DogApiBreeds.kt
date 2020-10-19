package com.example.dogapitest.mvp.model.repo

import com.example.dogapitest.mvp.model.api.IDataSource
import com.example.dogapitest.mvp.model.network.NetworkStatus
import io.reactivex.rxjava3.schedulers.Schedulers

class DogApiBreeds(
    val api: IDataSource,
    val networkStatus: NetworkStatus

) {
    fun getBreeds() = networkStatus.isOnlineSingle().flatMap {it  ->
        api.getBreeds()
    }.subscribeOn(Schedulers.io())



    fun getSubBreeds(breed: String) = networkStatus.isOnlineSingle().flatMap { it->
        api.getSubBreeds(breed)
    }.subscribeOn(Schedulers.io())
}