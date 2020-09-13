package com.example.dogapitest.mvp.model.entity.room.db

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity
data class RoomCachedBreeds(
    @PrimaryKey
    val breeds: String,  //Map<String,List<String>>

    val likeStatus: Int?,



//    @Relation(
//        parentColumn = "breeds",
//        entityColumn = "subBreeds"
//    )
    val subBreeds: String?,

//    @Relation(
//        parentColumn = "subBreeds",
//        entityColumn = "url"
//    )
    val url: String?
)
