package nitrosrus.ru.dogapitest.mvp.model.source

import nitrosrus.ru.dogapitest.mvp.model.api.IDataSource
import nitrosrus.ru.dogapitest.mvp.model.breedsModel.BreedsList
import nitrosrus.ru.dogapitest.mvp.model.breedsModel.SubBreedsList
import io.reactivex.Single

class ApiBreeds(val api: IDataSource) {

    fun getBreeds(): Single<BreedsList> = api.getBreeds()

    fun getSubBreeds(breed: String): Single<SubBreedsList> = api.getSubBreeds(breed)
}