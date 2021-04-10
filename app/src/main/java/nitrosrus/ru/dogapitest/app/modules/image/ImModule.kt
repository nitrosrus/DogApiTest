package nitrosrus.ru.dogapitest.app.modules.image

import nitrosrus.ru.dogapitest.mvp.model.api.IDataSource
import nitrosrus.ru.dogapitest.mvp.model.cache.IRoomFavouritesCache
import nitrosrus.ru.dogapitest.mvp.model.cache.room.RoomFavouritesCache
import nitrosrus.ru.dogapitest.mvp.model.entity.room.db.Database
import nitrosrus.ru.dogapitest.mvp.model.source.ApiImage
import nitrosrus.ru.dogapitest.rx.IRxProvider
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
