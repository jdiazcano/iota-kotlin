package com.jdiazcano.kota.model

import com.jdiazcano.kota.utils.MAX_SECURITY_LEVEL
import com.jdiazcano.kota.utils.MIN_SECURITY_LEVEL

typealias SecurityLevel = Int

fun Int.toSecurityLevel(): SecurityLevel = if (isValidSecurityLevel(this)) {
    this
} else {
    throw IllegalArgumentException("Security level must be valid: $this")
}

private fun isValidSecurityLevel(index: Int): Boolean {
    return index in MIN_SECURITY_LEVEL..MAX_SECURITY_LEVEL
}