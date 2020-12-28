package com.example.dogapitest.ui.network

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import com.example.dogapitest.R


class SharePhoto() : AppCompatDialogFragment() {

    companion object {
        fun newInstance(): SharePhoto {
            val dialogFragment = SharePhoto()
            return dialogFragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val context = activity
        return getAlertDialog(context!!)
    }

    fun getAlertDialog(context: Context): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setView(
            View.inflate(context, R.layout.dialog_share_photo, null)
                .apply {
                    findViewById<Button>(R.id.btn_share).setOnClickListener { dialog?.dismiss() }
                    findViewById<Button>(R.id.btn_cancel).setOnClickListener { dialog?.dismiss() }
                })
        return builder.create()
    }




}


