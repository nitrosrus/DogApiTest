package nitrosrus.ru.dogapitest.app.modules

import nitrosrus.ru.dogapitest.App
import nitrosrus.ru.dogapitest.ui.dialog.IShowAlertDialog
import nitrosrus.ru.dogapitest.ui.dialog.ShowAlertDialog
import dagger.Module
import dagger.Provides


@Module
class DialogModule {

    @Provides
    fun getDialogModule(app: App): IShowAlertDialog {
        return ShowAlertDialog(app.baseContext)
    }
}