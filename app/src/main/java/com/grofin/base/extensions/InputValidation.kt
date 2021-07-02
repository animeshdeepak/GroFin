package com.grofin.base.extensions


fun String.isMobileValid(): Boolean {
    return !isNullOrEmpty() && !isNullOrBlank() && trim().length == 10
}

fun String.isOtpValid(): Boolean {
    return !isNullOrEmpty() && !isNullOrBlank() && trim().length == 6
}

fun String.isReferIdValid(): Boolean {
    return !isNullOrEmpty() && !isNullOrBlank() && trim().length == 10
}

