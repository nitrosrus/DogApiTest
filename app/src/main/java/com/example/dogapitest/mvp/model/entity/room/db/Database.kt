package com.example.dogapitest.mvp.model.entity.room.db

import androidx.room.RoomDatabase
import com.example.dogapitest.mvp.model.entity.RoomFavourites
import com.example.dogapitest.mvp.model.entity.RoomImage
import com.example.dogapitest.mvp.model.entity.room.dao.BreedsDao
import com.example.dogapitest.mvp.model.entity.room.dao.ImageDao

@androidx.room.Database(
    entities = [
        RoomImage::class,
        RoomFavourites::class
    ], version = 1
)


abstract class Database : RoomDatabase() {
    abstract val breedsDao: BreedsDao
    abstract val imageDao: ImageDao


    companion object {
        const val DB_NAME = "breedsDataBase.db"
    }

}