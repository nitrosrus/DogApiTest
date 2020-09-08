package com.example.dogapitest.di.app

import com.example.dogapitest.di.app.modules.ApiModule
import com.example.dogapitest.di.app.modules.AppModule
import com.example.dogapitest.di.app.modules.CiceroneModule
import com.example.dogapitest.di.breeds.BreedsSubcomponent
import com.example.dogapitest.mvp.presenter.MainPresenter
import com.example.dogapitest.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ApiModule::class
    ]
)
interface AppComponent {

    fun breedsSubcomponent(): BreedsSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}