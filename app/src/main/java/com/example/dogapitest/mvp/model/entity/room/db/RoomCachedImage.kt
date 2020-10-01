package com.example.dogapitest.mvp.model.entity.room.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class RoomCachedImage(
    @PrimaryKey val url: String,
    val localPath: String

)