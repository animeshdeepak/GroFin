package com.grofin.base.extensions

import java.util.regex.Pattern


fun String.isMobileValid(): Boolean {
    val pattern = Pattern.compile("(\\d{10})")
    val matcher = pattern.matcher(this.trim())
    return matcher.matches()
}

fun String.isOtpValid(): Boolean {
    val pattern = Pattern.compile("(\\d{4})")
    val matcher = pattern.matcher(this.trim())
    return matcher.matches()
}

fun String.isReferIdValid(): Boolean {
    val pattern = Pattern.compile("(\\d{10})")
    val matcher = pattern.matcher(this.trim())
    return matcher.matches()
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

