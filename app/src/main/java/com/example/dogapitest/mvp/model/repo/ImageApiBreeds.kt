package com.example.dogapitest.mvp.model.repo

import com.example.dogapitest.mvp.model.api.IDataSource
import com.example.dogapitest.mvp.model.breedsModel.ImageBreedsList
import com.example.dogapitest.mvp.model.cache.IBreedsCache
import com.example.dogapitest.mvp.model.network.NetworkStatus
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.core.CompletableOnSubscribe
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class ImageApiBreeds(
    val api: IDataSource,
    val networkStatus: NetworkStatus, val cache: IBreedsCache
) {



    fun getImage(breeds: String): Single<ImageBreedsList> {
        return api.getBreedsImage(breeds).subscribeOn(Schedulers.io())
    }

    fun getImage(breeds: String, subBreeds: String): Single<ImageBreedsList> {
        return api.getSubBreedsImage(breeds, subBreeds).subscribeOn(Schedulers.io())
    }


    fun getLikeImage(breed: String):Single<List<String>> {
       return cache.getLikeImages(breed).subscribeOn(Schedulers.io())
    }

    fun setLikeImage(breed: String, url: String) {
        cache.putLikeImage(breed, url).observeOn(AndroidSchedulers.mainThread())
    }


}