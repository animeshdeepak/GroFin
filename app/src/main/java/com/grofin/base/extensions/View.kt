package com.grofin.base.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun View.openKeyboard() {
    val keyboard = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    keyboard.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0)
}

fun View.closeKeyboard() {
    val keyboard = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    keyboard.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun String.showToast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

//making timer like 12:00
fun Long.getDurationString(): String {
    val min = (this % 3600) / 60
    val sec = this % 60

    return min.toInt().twoDigitString() + " : " + sec.toInt().twoDigitString()
}

private fun Int.twoDigitString(): String {
    if (this == 0)
        return "00"

    if (this / 10 == 0)
        return "0$this"

    return this.toString()
}