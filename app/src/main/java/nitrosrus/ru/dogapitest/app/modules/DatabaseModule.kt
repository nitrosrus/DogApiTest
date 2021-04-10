package nitrosrus.ru.dogapitest.app.modules


import androidx.room.Room
import nitrosrus.ru.dogapitest.App
import nitrosrus.ru.dogapitest.mvp.model.entity.room.db.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun database(app: App): Database {
        return Room.databaseBuilder(app, Database::class.java, Database.DB_NAME).build()
    }
}