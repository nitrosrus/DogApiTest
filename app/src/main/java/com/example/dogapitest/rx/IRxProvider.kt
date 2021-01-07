package com.example.dogapitest.rx

import io.reactivex.Scheduler


interface IRxProvider {
    fun uiMainThread(): Scheduler

    fun ioThread(): Scheduler
}