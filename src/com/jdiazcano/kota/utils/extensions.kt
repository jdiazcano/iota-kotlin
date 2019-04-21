package com.jdiazcano.kota.utils

fun Int.timesDo(operation: () -> Unit) {
    for (i in 0 until this) {
        operation()
    }
}