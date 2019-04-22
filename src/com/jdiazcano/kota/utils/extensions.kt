package com.jdiazcano.kota.utils

fun Int.timesDo(operation: () -> Unit) {
    for (i in 0 until this) {
        operation()
    }
}

fun <T> T.cast(type: String, validation: (T) -> Boolean) =
        if (validation(this)) this else throw IllegalArgumentException("Invalid $type: $this")
