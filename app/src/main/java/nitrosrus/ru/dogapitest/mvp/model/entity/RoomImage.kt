package nitrosrus.ru.dogapitest.mvp.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class RoomImage(
    @PrimaryKey val url: String,
    val localPath: String

)