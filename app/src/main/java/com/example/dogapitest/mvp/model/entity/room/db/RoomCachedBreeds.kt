package com.example.dogapitest.mvp.model.entity.room.db

import androidx.room.*


@Entity
data class RoomCachedBreeds(
    @PrimaryKey val breed: String

)
