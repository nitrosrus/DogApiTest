package com.example.dogapitest.mvp.model.api

import com.example.dogapitest.mvp.model.breedsModel.Example
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path


/*
LIST ALL BREEDS
https://dog.ceo/api/breeds/list/all

LIST ALL BREED IMAGES
https://dog.ceo/api/breed/{hound}/images

LIST ALL SUB-BREED
https://dog.ceo/api/breed/{hound}/list

LIST ALL SUB-BREED IMAGES
https://dog.ceo/api/breed/{hound breed}/{afghan subbreed}/images

 */
interface IDataSource {

    @GET("/api/breeds/list/all")
    fun getBreeds(): Single<Example>

    @GET("https://dog.ceo/api/breed/{hound}/list")
    fun getSubBreeds(@Path("hound") breed: String): Single<String>

    @GET("https://dog.ceo/api/breed/{hound}/images")
    fun getBreedsImage(@Path("hound") breeds: String)

    @GET("https://dog.ceo/api/breed/{hound}/{subBreed}/images")
    fun getSubBreedsImage(@Path("hound") breeds: String, @Path("subBreed") subBreed: String)


}