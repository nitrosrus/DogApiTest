package com.example.dogapitest.mvp.model.entity.room.db

import androidx.room.*


@Entity
data class RoomCacheLike(
    val breedName: String,
    @PrimaryKey val url: String

)

