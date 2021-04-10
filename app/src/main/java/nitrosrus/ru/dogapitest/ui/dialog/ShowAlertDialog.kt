package nitrosrus.ru.dogapitest.ui.dialog

import android.content.Context
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import nitrosrus.ru.dogapitest.R


class ShowAlertDialog(context: Context) : AlertDialog(context), IShowAlertDialog {


    override var clickListener: (() -> Unit)? = null

    override fun getAlertInternet(context: Context): AlertDialog {
        return serverErrorInternet(context)
    }

    override fun getAlertSharePhoto(context: Context): AlertDialog {
        return sharePhotoDialog(context)
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
            dialog.dismiss()
        }
        btnCancel.setOnClickListener { dialog.dismiss() }
        return dialog
    }

    private fun sharePhotoDialog(context: Context): AlertDialog {
        val builder = Builder(context)
        val dialogView = layoutInflater.inflate(R.layout.dialog_share_photo, null)
        val btnOk = dialogView.findViewById<Button>(R.id.btn_share)
        val btnCancel = dialogView.findViewById<Button>(R.id.btn_cancel)
        builder.setView(dialogView)
        val dialog = builder.create()
        btnOk.setOnClickListener {
            clickListener?.invoke()
            dialog.dismiss()
        }
        btnCancel.setOnClickListener { dialog.dismiss() }
        return dialog
    }


}


