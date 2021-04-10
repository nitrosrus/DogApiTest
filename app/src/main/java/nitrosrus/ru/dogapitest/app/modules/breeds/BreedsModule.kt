package nitrosrus.ru.dogapitest.app.modules.breeds

import nitrosrus.ru.dogapitest.mvp.model.api.IDataSource
import nitrosrus.ru.dogapitest.mvp.model.cache.IRoomFavouritesCache
import nitrosrus.ru.dogapitest.mvp.model.cache.room.RoomFavouritesCache
import nitrosrus.ru.dogapitest.mvp.model.entity.room.db.Database

import nitrosrus.ru.dogapitest.mvp.model.source.ApiBreeds
import nitrosrus.ru.dogapitest.rx.IRxProvider
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
    fun breedsCache(database: Database, rxProvider: IRxProvider): IRoomFavouritesCache {
        return RoomFavouritesCache(database,rxProvider)
    }

}