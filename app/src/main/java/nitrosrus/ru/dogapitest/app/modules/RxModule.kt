package nitrosrus.ru.dogapitest.app.modules

import nitrosrus.ru.dogapitest.rx.IRxProvider
import nitrosrus.ru.dogapitest.rx.RxProvider
import dagger.Module
import dagger.Provides


@Module
class RxModule {

    @Provides
    fun getRxProvider(): IRxProvider {
        return RxProvider()
    }

}