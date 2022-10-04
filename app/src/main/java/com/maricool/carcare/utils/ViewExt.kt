package com.maricool.carcare.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.maricool.carcare.R


fun View.forceHideKeyboard() {
    val inputManager: InputMethodManager =
        this.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManager.hideSoftInputFromWindow(this.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
}

fun View.showSnackBar(text: Int) {
    Snackbar.make(this.rootView.findViewById(R.id.container), text, Snackbar.LENGTH_SHORT).show()
}

fun Context.showToast(text: Int){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}