package nitrosrus.ru.dogapitest.app


import nitrosrus.ru.dogapitest.app.modules.breeds.BreedsSubcomponent
import nitrosrus.ru.dogapitest.app.modules.image.ImageSubcomponent
import nitrosrus.ru.dogapitest.mvp.presenter.MainPresenter
import nitrosrus.ru.dogapitest.ui.activity.MainActivity
import dagger.Component
import nitrosrus.ru.dogapitest.app.modules.*
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