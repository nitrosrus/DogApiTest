package com.example.dogapitest.mvp.model.entity.room.dao


import androidx.room.*
import com.example.dogapitest.mvp.model.entity.room.db.RoomCacheLike
import io.reactivex.Single


@Dao
interface BreedsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: RoomCacheLike)

    @Delete
    fun delete(url: RoomCacheLike)

    @Query("SELECT * FROM RoomCacheLike")
    fun getAllLike(): Single<List<RoomCacheLike>>


}