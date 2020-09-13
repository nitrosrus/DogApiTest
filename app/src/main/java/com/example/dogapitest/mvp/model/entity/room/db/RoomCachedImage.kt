package com.example.dogapitest.mvp.model.entity.room.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation


@Entity
data class RoomCachedImage(

    @PrimaryKey val breeds: String,
    val url: String,
    val localPath: String,


//    @PrimaryKey val url: String,
//    val localPath: String,


)