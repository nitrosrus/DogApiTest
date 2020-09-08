package com.example.dogapitest

import android.app.Application
import com.example.dogapitest.di.app.AppComponent
import com.example.dogapitest.di.app.DaggerAppComponent
import com.example.dogapitest.di.app.modules.AppModule
import com.example.dogapitest.di.breeds.BreedsSubcomponent
import com.example.dogapitest.mvp.model.repo.DogApiBreeds
import dagger.internal.DaggerCollections


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

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }


}