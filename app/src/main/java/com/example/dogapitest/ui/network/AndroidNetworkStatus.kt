package com.example.dogapitest.ui.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class AndroidNetworkStatus(val context: Context) : NetworkStatus {
    companion object {
        const val NETWORK_CHECK_TIMEOUT_MILLS = 500L
    }

    private val statusSubject = BehaviorSubject.create<Boolean>()

    init {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val builder = NetworkRequest.Builder()
        connectivityManager.registerNetworkCallback(builder.build(),
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    statusSubject.onNext(true)
                }

                override fun onLost(network: Network) {
                    statusSubject.onNext(false)
                }

                override fun onUnavailable() {
                    statusSubject.onNext(false)
                }

                override fun onLosing(network: Network, maxMsToLive: Int) {
                    statusSubject.onNext(false)
                }
            })
    }

    override fun isOnline() = statusSubject.value

    override fun isOnlineObserver() = statusSubject

    override fun isOnlineSingle() =
        statusSubject.timeout(NETWORK_CHECK_TIMEOUT_MILLS, TimeUnit.MILLISECONDS)
            .onErrorReturnItem(false).first(false)


}