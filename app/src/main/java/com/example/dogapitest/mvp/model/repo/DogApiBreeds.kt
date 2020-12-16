package com.example.dogapitest.mvp.model.repo

import com.example.dogapitest.mvp.model.api.IDataSource
import com.example.dogapitest.mvp.model.breedsModel.BreedsList
import com.example.dogapitest.mvp.model.breedsModel.SubBreedsList
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class DogApiBreeds(
    val api: IDataSource
) {
    fun getBreeds(): Single<BreedsList> = api.getBreeds().subscribeOn(Schedulers.io())

    fun getSubBreeds(breed: String): Single<SubBreedsList> =
        api.getSubBreeds(breed).subscribeOn(Schedulers.io())
}