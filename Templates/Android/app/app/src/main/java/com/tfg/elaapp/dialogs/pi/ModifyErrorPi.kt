package com.tfg.elaapp.dialogs.pi

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.tfg.elaapp.MainActivity
import com.tfg.elaapp.R

class ModifyErrorPi{
    fun newInstance (context: Context, activity: Activity) : AlertDialog {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(context)
        dialog.setTitle(R.string.error_title)
        dialog.setMessage(R.string.message_error_ip_modified)
        dialog.setPositiveButton(R.string.ok) { wich, dialog->(activity as MainActivity)
                .callpersonalinformation()
        }

        dialog.setCancelable(false)
        return dialog.create()
    }

}