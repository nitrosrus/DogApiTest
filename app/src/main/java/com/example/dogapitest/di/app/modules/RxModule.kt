package com.example.dogapitest.di.app.modules

import com.example.dogapitest.rx.IRxProvider
import com.example.dogapitest.rx.RxProvider
import dagger.Module
import dagger.Provides


@Module
class RxModule {

    @Provides
    fun getRxProvider(): IRxProvider {
        return RxProvider()
    }

}