package com.jdiazcano.kota.utils

fun String.isTrytes(length: Int = this.length): Boolean {
    return matches(("^[A-Z9]{" + (if (length == 0) "0," else length) + "}$").toRegex())
}