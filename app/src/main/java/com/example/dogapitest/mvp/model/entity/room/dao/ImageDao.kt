package com.example.dogapitest.mvp.model.entity.room.dao

import androidx.room.*
import com.example.dogapitest.mvp.model.entity.room.db.RoomCachedImage


@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(breed: RoomCachedImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg breed: RoomCachedImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(breed: List<RoomCachedImage>)

    @Update
    fun update(breed: RoomCachedImage)

    @Update
    fun update(vararg breed: RoomCachedImage)

    @Update
    fun update(breed: List<RoomCachedImage>)

    @Delete
    fun delete(breed: RoomCachedImage)

    @Delete
    fun delete(vararg breed: RoomCachedImage)

    @Delete
    fun delete(breed: List<RoomCachedImage>)

    @Query("DELETE FROM RoomCachedImage")
    fun clear()

    @Query("SELECT * FROM RoomCachedImage")
    fun getAll(): List<RoomCachedImage>

    @Query("SELECT * FROM RoomCachedImage WHERE url = :url LIMIT 1")
    fun FindByUrl(url: String): RoomCachedImage?


}