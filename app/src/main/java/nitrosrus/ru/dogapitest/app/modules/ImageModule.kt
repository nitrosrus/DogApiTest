package nitrosrus.ru.dogapitest.app.modules

import android.widget.ImageView
import nitrosrus.ru.dogapitest.App
import nitrosrus.ru.dogapitest.mvp.model.cache.IImageCache
import nitrosrus.ru.dogapitest.mvp.model.cache.room.RoomImageCache
import nitrosrus.ru.dogapitest.mvp.model.entity.room.db.Database
import nitrosrus.ru.dogapitest.mvp.model.image.IImageLoader
import nitrosrus.ru.dogapitest.rx.IRxProvider
import nitrosrus.ru.dogapitest.ui.image.GlideImageLoader
import nitrosrus.ru.dogapitest.ui.network.NetworkStatus
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Named

@Module
class ImageModule {

    @Provides
    fun imageLoader(
        cache: IImageCache, networkStatus: NetworkStatus, rxProvider: IRxProvider
    ): IImageLoader<ImageView> {
        return GlideImageLoader(cache, networkStatus, rxProvider)
    }

    @Named("imageCachePath")
    @Provides
    fun imageCachePath(): String = (
            App.instance.externalCacheDir?.path
                ?: App.instance.getExternalFilesDir(null)?.path
                ?: App.instance.filesDir.path
            ) + File.separator + "image_cache"


    @Provides
    fun imageCache(@Named("imageCachePath") path: String, database: Database): IImageCache {
        return RoomImageCache(database, File(path))
    }

}