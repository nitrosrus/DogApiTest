package com.example.dogapitest.di.app.modules.breeds

import com.example.dogapitest.mvp.model.api.IDataSource
import com.example.dogapitest.mvp.model.cache.IRoomFavouritesCache
import com.example.dogapitest.mvp.model.cache.room.RoomFavouritesCache
import com.example.dogapitest.mvp.model.entity.room.db.Database

import com.example.dogapitest.mvp.model.source.ApiBreeds
import com.example.dogapitest.rx.IRxProvider
import dagger.Module
import dagger.Provides

@Module
open class BreedsModule {

    @BreedsScope
    @Provides
    open fun breedsRepo(api: IDataSource): ApiBreeds {
        return ApiBreeds(api)
    }

    @BreedsScope
    @Provides
    fun breedsCache(database: Database,rxProvider: IRxProvider): IRoomFavouritesCache {
        return RoomFavouritesCache(database,rxProvider)
    }

}