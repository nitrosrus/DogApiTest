package com.example.dogapitest.mvp.model.entity.room.dao


import androidx.room.*

import com.example.dogapitest.mvp.model.entity.room.db.RoomCacheLike
import com.example.dogapitest.mvp.model.entity.room.db.RoomCachedBreeds



@Dao
interface BreedsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(breeds: RoomCachedBreeds)

    @Delete
    fun delete(breeds: RoomCachedBreeds)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun Update(breeds: RoomCachedBreeds)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(url: RoomCacheLike)

    @Delete
    fun delete(url: RoomCacheLike)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun Update(url: RoomCacheLike)






    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(breeds: RoomCachedBreeds,url: RoomCacheLike)




    @Query("INSERT INTO RoomCachedBreeds (breed) VALUES (:breeds)")
    fun insertbreed(breeds: String)

    @Query("INSERT INTO RoomCacheLike (url) VALUES (:url)")
    fun inserturl(url: String)


    @Query("SELECT * FROM RoomCacheLike")
    fun getAllImage(): List<RoomCacheLike>


//    @Query("SELECT * FROM RoomCachedBreeds")
//    fun getLikeImages(): LiveData<List<RoomBreedsLikeFull>>
//
//    @Query("SELECT * FROM RoomCachedBreeds")
//    fun getAllDb(): List<RoomBreedsLikeFull>


}