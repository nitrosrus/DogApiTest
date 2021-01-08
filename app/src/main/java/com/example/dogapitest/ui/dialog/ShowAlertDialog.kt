package com.example.dogapitest.ui.dialog

import android.content.Context
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.dogapitest.R


class ShowAlertDialog(context: Context) : AlertDialog(context), IShowAlertDialog {


    override var clickListener: (() -> Unit)? = null

    override fun getAlertInternet(context: Context): AlertDialog {
        return serverErrorInternet(context)

    }

    override fun getAlertSharePhoto() {
        TODO("Not yet implemented")
    }


    private fun serverErrorInternet(context: Context): AlertDialog {
        val builder = Builder(context)
        val dialogView = layoutInflater.inflate(R.layout.dialog_server_error, null)
        val btnOk = dialogView.findViewById<Button>(R.id.btn_ok)
        val btnCancel = dialogView.findViewById<Button>(R.id.btn_cancel)
        builder.setView(dialogView)
        val dialog = builder.create()
        btnOk.setOnClickListener {
            clickListener?.invoke()
            if (dialog != null) {
                dialog.dismiss()
            }
        }
        btnCancel.setOnClickListener { dialog.dismiss() }
        return dialog
    }

    private fun sharePhotoDialog(context: Context) {


    }


//    companion object {
//        fun newInstance(): SharePhoto {
//            val dialogFragment = SharePhoto()
//            return dialogFragment
//        }
//    }
//
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        val context = activity
//        return getAlertDialog(context!!)
//    }
//
//    fun getAlertDialog(context: Context): AlertDialog {
//        val builder = AlertDialog.Builder(context)
//        builder.setView(
//            View.inflate(context, R.layout.dialog_share_photo, null)
//                .apply {
//                    findViewById<Button>(R.id.btn_share).setOnClickListener { dialog?.dismiss() }
//                    findViewById<Button>(R.id.btn_cancel).setOnClickListener { dialog?.dismiss() }
//                })
//        return builder.create()
//    }
}


