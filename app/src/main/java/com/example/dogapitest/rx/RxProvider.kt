package com.example.dogapitest.rx

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

class RxProvider : IRxProvider {
    override fun uiMainThread(): Scheduler = AndroidSchedulers.mainThread()

    override fun ioThread(): Scheduler = Schedulers.io()


}