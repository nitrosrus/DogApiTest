package com.example.dogapitest.ui.dialog

import android.content.Context
import androidx.appcompat.app.AlertDialog

interface IShowAlertDialog {
    var clickListener: (() -> Unit)?

    fun getAlertInternet(context: Context): AlertDialog
    fun getAlertSharePhoto()
}