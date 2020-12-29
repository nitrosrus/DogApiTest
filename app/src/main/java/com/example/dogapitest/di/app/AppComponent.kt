package com.example.dogapitest.di.app


import com.example.dogapitest.di.app.modules.*
import com.example.dogapitest.di.app.modules.breeds.BreedsSubcomponent
import com.example.dogapitest.di.app.modules.image.ImageSubcomponent
import com.example.dogapitest.mvp.presenter.MainPresenter
import com.example.dogapitest.ui.activity.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        ApiModule::class,
        ImageModule::class,
        DatabaseModule::class,
        RxModule::class,
        DialogModule::class

    ]
)
interface AppComponent {

    fun breedsSubcomponent(): BreedsSubcomponent
    fun imageSubcomponent(): ImageSubcomponent

    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}