package com.example.dogapitest.mvp.model.entity.room.dao


import androidx.room.*
import com.example.dogapitest.mvp.model.entity.room.db.RoomCacheLike


@Dao
interface BreedsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: RoomCacheLike)


    @Delete
    fun delete(url: RoomCacheLike)

//    @Update(onConflict = OnConflictStrategy.REPLACE)
//    fun Update(url: RoomCacheLike)

//    @Query("INSERT INTO RoomCacheLike (url) VALUES (:url)")
//    fun inserturl(url: String)




    @Query("SELECT * FROM RoomCacheLike")
    fun getAllLike(): List<RoomCacheLike>


}