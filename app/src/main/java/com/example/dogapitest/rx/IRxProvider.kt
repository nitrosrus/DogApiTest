package com.example.dogapitest.rx

import io.reactivex.rxjava3.core.Scheduler


interface IRxProvider {
    fun uiMainThread(): Scheduler

    fun ioThread(): Scheduler
}