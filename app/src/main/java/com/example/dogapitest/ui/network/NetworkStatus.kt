package com.example.dogapitest.ui.network


import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface NetworkStatus {
    fun isOnline(): Boolean?
    fun isOnlineObserver():Observable <Boolean>
    fun isOnlineSingle(): Single<Boolean>

}