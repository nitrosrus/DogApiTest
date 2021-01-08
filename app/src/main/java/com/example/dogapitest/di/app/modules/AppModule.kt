package com.example.dogapitest.di.app.modules

import com.example.dogapitest.App
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }


}