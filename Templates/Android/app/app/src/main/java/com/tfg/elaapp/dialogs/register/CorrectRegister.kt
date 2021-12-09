package com.tfg.elaapp.dialogs.register

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.tfg.elaapp.MainActivity
import com.tfg.elaapp.R

class CorrectRegister {
    fun newInstance (context: Context, activity: Activity) : AlertDialog {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(context)
        dialog.setTitle(R.string.tittle_register)
        dialog.setMessage(R.string.ok_register)
        dialog.setPositiveButton(R.string.ok) { wich, dialog->(activity as MainActivity).mainFrag()
        }

        dialog.setCancelable(false)
        return dialog.create()
    }
}