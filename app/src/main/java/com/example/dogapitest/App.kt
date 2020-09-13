package com.example.dogapitest

import android.app.Application
import com.example.dogapitest.di.app.AppComponent
import com.example.dogapitest.di.app.DaggerAppComponent
import com.example.dogapitest.di.app.modules.AppModule
import com.example.dogapitest.di.app.modules.breeds.BreedsSubcomponent
import com.example.dogapitest.di.app.modules.image.ImageSubcomponent
import timber.log.Timber


class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    private var bComponent: BreedsSubcomponent? = null

    val breeedsComponent: BreedsSubcomponent
        get() = appComponent.breedsSubcomponent().also {
            bComponent = it
        }

    private var iComponent: ImageSubcomponent? = null

    val imageComponent: ImageSubcomponent
        get() = appComponent.imageSubcomponent().also {
            iComponent = it
        }


    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }


}