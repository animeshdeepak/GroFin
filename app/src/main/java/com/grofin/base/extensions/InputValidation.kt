package com.grofin.base.extensions

import java.util.regex.Pattern


fun String.isMobileValid(): Boolean {
//    return !isNullOrEmpty() && !isNullOrBlank() && trim().length == 10
    val pattern = Pattern.compile("(\\d{10})")
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isOtpValid(): Boolean {
//    return !isNullOrEmpty() && !isNullOrBlank() && trim().length == 4
    val pattern = Pattern.compile("(\\d{4})")
    val matcher = pattern.matcher(this)
    return matcher.matches()
}

fun String.isReferIdValid(): Boolean {
    return !isNullOrEmpty() && !isNullOrBlank() && trim().length == 10
}

fun String.extractOtp(): String? {
    //   \d is for a digit
    //   {} is the number of digits here 4.
    val pattern = Pattern.compile("(\\d{4})")
    val matcher = pattern.matcher(this)
    return if (matcher.find())
        matcher.group(0)
    else null
}

