package com.example.dogapitest.mvp.model.entity.room.db

import androidx.annotation.NonNull
import androidx.room.*


@Entity(
    foreignKeys =
        [ForeignKey(
            entity = RoomCachedBreeds::class,
            parentColumns = ["breed"],
            childColumns = ["breedName"]
        )]
    )

class RoomCacheLike(

    val breedName: String,
   @PrimaryKey val url: String
)
