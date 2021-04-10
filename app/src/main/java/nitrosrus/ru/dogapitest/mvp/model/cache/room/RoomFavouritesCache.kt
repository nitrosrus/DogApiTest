package nitrosrus.ru.dogapitest.mvp.model.cache.room


import nitrosrus.ru.dogapitest.mvp.model.cache.IRoomFavouritesCache
import nitrosrus.ru.dogapitest.mvp.model.entity.room.db.Database
import nitrosrus.ru.dogapitest.mvp.model.entity.RoomFavourites
import nitrosrus.ru.dogapitest.rx.IRxProvider
import io.reactivex.Completable
import io.reactivex.Single

class RoomFavouritesCache(val database: Database, var rxProvider: IRxProvider) :
    IRoomFavouritesCache {


    override fun getAllData(): Single<List<RoomFavourites>> {
        return database.breedsDao.getAllLike()
    }

    override fun putLikeImage(breed: String, url: String) = Completable.fromAction {

        database.breedsDao.insert(RoomFavourites(breedName = breed, url = url))

    }.subscribeOn(rxProvider.ioThread())

    override fun putDisLike(breed: String, url: String) = Completable.fromAction {
        database.breedsDao.delete(RoomFavourites(breedName = breed, url = url))
    }.subscribeOn(rxProvider.ioThread())


}


