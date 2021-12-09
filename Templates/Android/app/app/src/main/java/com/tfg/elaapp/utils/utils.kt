package com.tfg.elaapp.utils

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.view.inputmethod.InputMethodManager

fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.
    matcher(this).matches()
}

fun hideSoftKeyboard(activity: Activity) {
    if (activity.getCurrentFocus() == null){
        return
    }
    val inputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus()!!.getWindowToken(), 0)
}