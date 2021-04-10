package nitrosrus.ru.dogapitest.mvp.model.api

import nitrosrus.ru.dogapitest.mvp.model.breedsModel.BreedsList
import nitrosrus.ru.dogapitest.mvp.model.breedsModel.ImageBreedsList
import nitrosrus.ru.dogapitest.mvp.model.breedsModel.SubBreedsList
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface IDataSource {

    @GET("/api/breeds/list/all")
    fun getBreeds(): Single<BreedsList>

    @GET("/api/breed/{hound}/list")
    fun getSubBreeds(@Path("hound") breed: String): Single<SubBreedsList>

    @GET("/api/breed/{hound}/images")
    fun getBreedsImage(@Path("hound") breeds: String): Single<ImageBreedsList>

    @GET("/api/breed/{hound}/{subBreed}/images")
    fun getSubBreedsImage(
        @Path("hound") breeds: String,
        @Path("subBreed") subBreed: String
    ): Single<ImageBreedsList>


}