package com.example.dogapitest.di.app.modules.breeds

import com.example.dogapitest.mvp.model.api.IDataSource
import com.example.dogapitest.mvp.model.cache.IBreedsCache
import com.example.dogapitest.mvp.model.cache.room.RoomBreedsCache
import com.example.dogapitest.mvp.model.entity.room.db.Database

import com.example.dogapitest.mvp.model.repo.DogApiBreeds
import dagger.Module
import dagger.Provides

@Module
open class BreedsModule {

    @BreedsScope
    @Provides
    open fun breedsRepo(api: IDataSource): DogApiBreeds {
        return DogApiBreeds(api)
    }
    @BreedsScope
    @Provides
    fun breedsCache(database: Database): IBreedsCache {
        return RoomBreedsCache(database)
    }

}