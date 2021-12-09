package com.tfg.elaapp.dialogs.timeEat

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import com.tfg.elaapp.MainActivity
import com.tfg.elaapp.R

class CorrecAddTimeEat {
    fun newInstance (context: Context, activity: Activity) : AlertDialog {
        val dialog: AlertDialog.Builder = AlertDialog.Builder(context)
        dialog.setTitle(R.string.informacio)
        dialog.setMessage(R.string.text_info_eat)
        dialog.setPositiveButton(R.string.ok) { wich, dialog->(activity as MainActivity).mainFrag()
        }

        dialog.setCancelable(false)
        return dialog.create()
    }
}