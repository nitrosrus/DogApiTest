package nitrosrus.ru.dogapitest

import android.app.Application
import nitrosrus.ru.dogapitest.app.AppComponent
import nitrosrus.ru.dogapitest.app.DaggerAppComponent
import nitrosrus.ru.dogapitest.app.modules.AppModule
import nitrosrus.ru.dogapitest.app.modules.breeds.BreedsSubcomponent
import nitrosrus.ru.dogapitest.app.modules.image.ImageSubcomponent
import timber.log.Timber


class App : Application() {

    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    private var bComponent: BreedsSubcomponent? = null

    val breedsComponent: BreedsSubcomponent
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