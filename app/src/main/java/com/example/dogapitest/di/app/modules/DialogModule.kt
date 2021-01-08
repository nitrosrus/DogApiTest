package com.example.dogapitest.di.app.modules

import com.example.dogapitest.App
import com.example.dogapitest.ui.dialog.IShowAlertDialog
import com.example.dogapitest.ui.dialog.ShowAlertDialog
import dagger.Module
import dagger.Provides


@Module
class DialogModule {

    @Provides
    fun getDialogModule(app: App): IShowAlertDialog {
        return ShowAlertDialog(app.baseContext)
    }
}