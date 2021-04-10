package nitrosrus.ru.dogapitest.mvp.model.entity.room.dao


import androidx.room.*
import nitrosrus.ru.dogapitest.mvp.model.entity.RoomFavourites
import io.reactivex.Single


@Dao
interface BreedsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg entity: RoomFavourites)

    @Delete
    fun delete(url: RoomFavourites)

    @Query("SELECT * FROM RoomFavourites")
    fun getAllLike(): Single<List<RoomFavourites>>


}