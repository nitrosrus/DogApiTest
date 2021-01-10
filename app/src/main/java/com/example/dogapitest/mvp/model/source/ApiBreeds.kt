package com.example.dogapitest.mvp.model.source

import com.example.dogapitest.mvp.model.api.IDataSource
import com.example.dogapitest.mvp.model.breedsModel.BreedsList
import com.example.dogapitest.mvp.model.breedsModel.SubBreedsList
import io.reactivex.Single

class ApiBreeds(val api: IDataSource) {

    fun getBreeds(): Single<BreedsList> = api.getBreeds()

    fun getSubBreeds(breed: String): Single<SubBreedsList> = api.getSubBreeds(breed)
}