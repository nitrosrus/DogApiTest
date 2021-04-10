package nitrosrus.ru.dogapitest.mvp.model.entity

import androidx.room.*


@Entity
data class RoomFavourites(
    val breedName: String,
    @PrimaryKey val url: String
)

