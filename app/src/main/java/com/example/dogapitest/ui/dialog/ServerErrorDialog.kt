package com.example.dogapitest.ui.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.dogapitest.R
import kotlinx.android.synthetic.main.dialog_server_error.*

class ServerErrorDialog : DialogFragment() {

    companion object {
        fun newInstance() = ServerErrorDialog()
    }


//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//
////        val builder = AlertDialog.Builder(requireContext()).apply {
////            setView(R.layout.dialog_server_error)
////
////        }
//
//        return builder.create()
//    }


}