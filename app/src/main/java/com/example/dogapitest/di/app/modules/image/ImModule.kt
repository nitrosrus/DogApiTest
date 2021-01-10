package com.example.dogapitest.di.app.modules.image

import com.example.dogapitest.mvp.model.api.IDataSource
import com.example.dogapitest.mvp.model.cache.IRoomFavouritesCache
import com.example.dogapitest.mvp.model.cache.room.RoomFavouritesCache
import com.example.dogapitest.mvp.model.entity.room.db.Database
import com.example.dogapitest.mvp.model.source.ApiImage
import com.example.dogapitest.rx.IRxProvider
import dagger.Module
import dagger.Provides


@Module
open class ImModule {

    @ImageScope
    @Provides
    open fun apiImage(api: IDataSource): ApiImage {
        return ApiImage(api)
    }

    @ImageScope
    @Provides
    fun favouritesCache(database: Database, rxProvider: IRxProvider): IRoomFavouritesCache {
        return RoomFavouritesCache(database, rxProvider)
    }


}
