package com.example.dogapitest.di.app.modules

import android.widget.ImageView
import com.example.dogapitest.App
import com.example.dogapitest.mvp.model.cache.image.IImageCache
import com.example.dogapitest.mvp.model.cache.image.room.RoomImageCache
import com.example.dogapitest.mvp.model.entity.room.db.Database
import com.example.dogapitest.mvp.model.image.IImageLoader
import com.example.dogapitest.mvp.model.network.NetworkStatus
import com.example.dogapitest.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import java.io.File
import javax.inject.Named

@Module
class ImageModule {

    @Provides
    fun imageLoader(cache: IImageCache, networkStatus: NetworkStatus): IImageLoader<ImageView> {
        return GlideImageLoader(cache, networkStatus)
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