package com.example.dogapitest.di.app.modules.image

import com.example.dogapitest.mvp.model.api.IDataSource
import com.example.dogapitest.mvp.model.cache.IBreedsCache
import com.example.dogapitest.mvp.model.cache.room.RoomBreedsCache
import com.example.dogapitest.mvp.model.entity.room.db.Database
import com.example.dogapitest.mvp.model.repo.ImageApiBreeds
import com.example.dogapitest.rx.IRxProvider
import dagger.Module
import dagger.Provides


@Module
open class ImModule {

    @ImageScope
    @Provides
    open fun imageRepo(api: IDataSource): ImageApiBreeds {
        return ImageApiBreeds(api)
    }

    @ImageScope
    @Provides
    fun breedsCache(database: Database, rxProvider: IRxProvider): IBreedsCache {
        return RoomBreedsCache(database, rxProvider)
    }


}
