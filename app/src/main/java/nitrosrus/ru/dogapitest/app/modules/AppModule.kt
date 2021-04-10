package nitrosrus.ru.dogapitest.app.modules

import nitrosrus.ru.dogapitest.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }


}