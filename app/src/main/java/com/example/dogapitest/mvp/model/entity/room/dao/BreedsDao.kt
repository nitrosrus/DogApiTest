package com.example.dogapitest.mvp.model.entity.room.dao

import androidx.room.*

import com.example.dogapitest.mvp.model.entity.room.db.RoomCachedBreeds

@Dao
interface BreedsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(breed: RoomCachedBreeds)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg breed: RoomCachedBreeds)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(breed: List<RoomCachedBreeds>)

    @Update
    fun update(breed: RoomCachedBreeds)

    @Update
    fun update(vararg breed: RoomCachedBreeds)

    @Update
    fun update(breed: List<RoomCachedBreeds>)

    @Delete
    fun delete(breed: RoomCachedBreeds)

    @Delete
    fun delete(vararg breed: RoomCachedBreeds)

    @Delete
    fun delete(breed: List<RoomCachedBreeds>)

    @Query("DELETE FROM RoomCachedBreeds")
    fun clear()

    @Query("SELECT * FROM RoomCachedBreeds")
    fun getAll(): List<RoomCachedBreeds>

    @Query("SELECT * FROM RoomCachedBreeds WHERE breeds = :breeds LIMIT 1")
    fun FindByBreeds(breeds: String): RoomCachedBreeds




}