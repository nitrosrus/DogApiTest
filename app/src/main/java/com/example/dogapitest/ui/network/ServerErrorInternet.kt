package com.example.dogapitest.ui.network

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.dogapitest.R


class ServerErrorInternet() : AppCompatDialogFragment() {
    companion object {
        fun newInstance(): ServerErrorInternet {
            return ServerErrorInternet()
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = activity
        return getAlertDialog(context!!)
    }

    fun getAlertDialog(context: Context): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setView(
            View.inflate(context, R.layout.dialog_server_error, null)
                .apply { findViewById<Button>(R.id.btn_ok).setOnClickListener { dialog?.dismiss() } })
        return builder.create()
    }


}


