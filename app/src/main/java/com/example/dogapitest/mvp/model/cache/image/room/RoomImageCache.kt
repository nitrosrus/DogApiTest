package com.example.dogapitest.mvp.model.cache.image.room


import com.example.dogapitest.mvp.model.cache.image.IImageCache
import com.example.dogapitest.mvp.model.entity.room.db.Database
import com.example.dogapitest.mvp.model.entity.room.db.RoomCachedImage
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import java.security.MessageDigest


class RoomImageCache(val database: Database, val dir: File) : IImageCache {

    private fun String.md5() = hash("MD5")
    private fun String.hash(algoritm: String) =
        MessageDigest.getInstance(algoritm).digest(toByteArray())
            .fold("", { str, it -> "%02x".format(it) })


    override fun getBytes(url: String) = Maybe.fromCallable {
        database.imageDao.FindByUrl(url)?.let {
            File(it.localPath).inputStream().readBytes()
        }
    }.subscribeOn(Schedulers.io())

    override fun saveImage(url: String, bytes: ByteArray) = Completable.create { emitter ->
        if (!dir.exists() && !dir.mkdir()) {
            emitter.onError(IOException("Failed to create cache dir"))
            return@create
        }
        val fileFormat = if (url.contains(".jpg")) ".jpg" else ".png"
        val imageFile = File(dir, url.md5() + fileFormat)
        try {
            FileOutputStream(imageFile).use {
                it.write(bytes)
            }
        } catch (e: Exception) {
            emitter.onError(e)
        }
        database.imageDao.insert(RoomCachedImage(url,imageFile.path))
        emitter.onComplete()

    }.subscribeOn(Schedulers.io())

    override fun contains(url: String) =
        Single.fromCallable { database.imageDao.FindByUrl(url) != null }
            .subscribeOn(Schedulers.io())


    override fun clear() = Completable.fromAction {
        database.imageDao.clear()
        dir.deleteRecursively()
    }.subscribeOn(Schedulers.io())
}