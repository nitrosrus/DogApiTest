package com.example.dogapitest.di.app.modules.image

import com.example.dogapitest.mvp.model.api.IDataSource
import com.example.dogapitest.mvp.model.network.NetworkStatus
import com.example.dogapitest.mvp.model.repo.ImageApiBreeds
import dagger.Module
import dagger.Provides


@Module
open class ImModule {

    @ImageScope
    @Provides
    open fun imageRepo(api: IDataSource, networkStatus: NetworkStatus): ImageApiBreeds {
        return ImageApiBreeds(api, networkStatus)
    }

}
