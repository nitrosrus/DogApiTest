package com.example.dogapitest.di.app.modules.breeds

import com.example.dogapitest.mvp.model.api.IDataSource
import com.example.dogapitest.mvp.model.network.NetworkStatus
import com.example.dogapitest.mvp.model.repo.DogApiBreeds
import dagger.Module
import dagger.Provides

@Module
open class BreedsModule {

    @BreedsScope
    @Provides
    open fun breedsRepo(api: IDataSource, networkStatus: NetworkStatus): DogApiBreeds {
        return DogApiBreeds(api, networkStatus)
    }

}