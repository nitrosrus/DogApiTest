package nitrosrus.ru.dogapitest.ui.network


import io.reactivex.Observable
import io.reactivex.Single

interface NetworkStatus {
    fun isOnline(): Boolean?
    fun isOnlineObserver():Observable <Boolean>
    fun isOnlineSingle(): Single<Boolean>

}