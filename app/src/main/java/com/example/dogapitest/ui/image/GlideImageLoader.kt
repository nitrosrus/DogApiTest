package com.example.dogapitest.ui.image

import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.dogapitest.R
import com.bumptech.glide.request.target.Target
import com.example.dogapitest.mvp.model.cache.IImageCache
import com.example.dogapitest.mvp.model.image.IImageLoader
import com.example.dogapitest.rx.IRxProvider
import com.example.dogapitest.ui.network.NetworkStatus
import io.reactivex.android.schedulers.AndroidSchedulers
import timber.log.Timber
import java.io.ByteArrayOutputStream
import javax.inject.Inject

class GlideImageLoader(
    override val cache: IImageCache, val networkStatus: NetworkStatus, var rxProvider: IRxProvider
) :
    IImageLoader<ImageView> {


    override fun loadInto(url: String, container: ImageView, preload: ImageView) {
        Timber.d("Loading Image $url ")
        networkStatus.isOnlineSingle().observeOn(rxProvider.uiMainThread())
            .subscribe { isOnLine ->
                if (isOnLine) {
                    Timber.d("Online. Loading image from internet")
                    Glide.with(preload.context).load(R.raw.load).into(preload)
                    Glide.with(container.context).asBitmap().load(url)
                        .listener(object : RequestListener<Bitmap> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: Target<Bitmap>?,
                                isFirstResource: Boolean
                            ) = Timber.d("Failed to load image $url ").let { true }

                            override fun onResourceReady(
                                resource: Bitmap?,
                                model: Any?,
                                target: Target<Bitmap>?,
                                dataSource: DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                val compressFormat = if (url.contains(".jpg"))
                                    Bitmap.CompressFormat.JPEG else Bitmap.CompressFormat.PNG
                                val stream = ByteArrayOutputStream()
                                resource?.compress(compressFormat, 100, stream)
                                val bytes = stream.use { it.toByteArray() }
                                cache.saveImage(url, bytes).blockingAwait()
                                return false
                            }
                        })
                        .into(container)
                } else {
                    Timber.d("Offline. Loading image from cache")
                    cache.getBytes(url).observeOn(AndroidSchedulers.mainThread()).subscribe({
                        Glide.with(container.context).load(it).into(container)
                    }, {
                        Timber.e(it)
                    })
                }

            }


    }
}