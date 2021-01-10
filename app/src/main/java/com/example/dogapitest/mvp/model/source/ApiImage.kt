package com.example.dogapitest.mvp.model.source

import com.example.dogapitest.mvp.model.api.IDataSource
import com.example.dogapitest.mvp.model.breedsModel.ImageBreedsList
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers


class ApiImage(val api: IDataSource) {

    fun getImage(breeds: String): Single<ImageBreedsList>
            = api.getBreedsImage(breeds).subscribeOn(Schedulers.io())


    fun getImage(breeds: String, subBreeds: String): Single<ImageBreedsList>
            = api.getSubBreedsImage(breeds, subBreeds).subscribeOn(Schedulers.io())






}