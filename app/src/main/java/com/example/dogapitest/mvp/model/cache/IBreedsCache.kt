package com.example.dogapitest.mvp.model.cache

import com.example.dogapitest.mvp.model.breedsModel.Example
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IBreedsCache {

    fun getBreeds(): Single<Example>
    fun putBreeds(breeds: Example): Completable

}