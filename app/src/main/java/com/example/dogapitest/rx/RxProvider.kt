package com.example.dogapitest.rx

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class RxProvider : IRxProvider {
    override fun uiMainThread(): Scheduler = AndroidSchedulers.mainThread()

    override fun ioThread(): Scheduler = Schedulers.io()


}