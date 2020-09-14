package com.example.dogapitest.mvp.model.entity.room.db

import androidx.room.RoomDatabase
import com.example.dogapitest.mvp.model.entity.room.dao.BreedsDao
import com.example.dogapitest.mvp.model.entity.room.dao.ImageDao

@androidx.room.Database(
    entities = [
        RoomCachedBreeds::class,
        RoomCachedImage::class
    ], version = 1
)
abstract class Database : RoomDatabase() {
    abstract val breedsDao: BreedsDao
    abstract val imageDao: ImageDao

    companion object {
        const val DB_NAME = "breedsDataBase.db"
    }

}