package com.tfg.elaapp.dialogs.register

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.tfg.elaapp.MainActivity
import com.tfg.elaapp.R

class ErrorRegister {

    fun newInstance (context: Context, activity: Activity) : AlertDialog {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(context)
        dialog.setIcon(R.drawable.warning)
        dialog.setTitle(R.string.error_register)
        dialog.setMessage(R.string.error_register_message)
        dialog.setPositiveButton(R.string.ok) { wich, dialog->(activity as MainActivity).callregister()
        }
        dialog.setCancelable(false)
        return dialog.create()
    }
}
