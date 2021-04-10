package nitrosrus.ru.dogapitest.mvp.model.entity.room.dao

import androidx.room.*
import nitrosrus.ru.dogapitest.mvp.model.entity.RoomImage


@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(breed: RoomImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg breed: RoomImage)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(breed: List<RoomImage>)

    @Update
    fun update(breed: RoomImage)

    @Update
    fun update(vararg breed: RoomImage)

    @Update
    fun update(breed: List<RoomImage>)

    @Delete
    fun delete(breed: RoomImage)

    @Delete
    fun delete(vararg breed: RoomImage)

    @Delete
    fun delete(breed: List<RoomImage>)

    @Query("DELETE FROM RoomImage")
    fun clear()

    @Query("SELECT * FROM RoomImage")
    fun getAll(): List<RoomImage>

    @Query("SELECT * FROM RoomImage WHERE url = :url LIMIT 1")
    fun FindByUrl(url: String): RoomImage?


}