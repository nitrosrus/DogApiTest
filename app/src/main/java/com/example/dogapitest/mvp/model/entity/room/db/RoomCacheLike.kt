package com.example.dogapitest.mvp.model.entity.room.db

import androidx.room.*


@Entity
data class RoomCacheLike(
    @PrimaryKey val url: String,
    val breedName: String
)

