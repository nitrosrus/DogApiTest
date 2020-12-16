package com.example.dogapitest.mvp.model.repo

import com.example.dogapitest.mvp.model.api.IDataSource
import com.example.dogapitest.mvp.model.breedsModel.ImageBreedsList
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


class ImageApiBreeds(val api: IDataSource) {

    fun getImage(breeds: String): Single<ImageBreedsList>
            = api.getBreedsImage(breeds).subscribeOn(Schedulers.io())


    fun getImage(breeds: String, subBreeds: String): Single<ImageBreedsList>
            = api.getSubBreedsImage(breeds, subBreeds).subscribeOn(Schedulers.io())






}