package com.grofin.base.extensions


fun String.isMobileValid(): Boolean {
    // TODO(add valid regex)
    return !isNullOrEmpty() && !isNullOrBlank() && trim().length == 10
}

fun String.isOtpValid(): Boolean {
    // TODO(add valid regex)
    return !isNullOrEmpty() && !isNullOrBlank() && trim().length == 6
}

fun String.isReferIdValid(): Boolean {
    return !isNullOrEmpty() && !isNullOrBlank() && trim().length == 10
}

