package com.example.dogapitest.di.app.modules

import android.widget.ImageView
import com.example.dogapitest.App
import com.example.dogapitest.mvp.model.cache.IImageCache
import com.example.dogapitest.mvp.model.cache.room.RoomImageCache
import com.example.dogapitest.mvp.model.entity.room.db.Database
import com.example.dogapitest.mvp.model.image.IImageLoader
import com.example.dogapitest.rx.IRxProvider
import com.example.dogapitest.ui.image.GlideImageLoader
import com.example.dogapitest.ui.network.NetworkStatus
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Named

@Module
class ImageModule {

    @Provides
    fun imageLoader(
        cache: IImageCache, networkStatus: NetworkStatus,rxProvider: IRxProvider
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