package com.jdiazcano.kota.utils

import com.jdiazcano.kota.model.Transaction
import com.jdiazcano.kota.model.removeChecksum
import com.jdiazcano.kota.model.toTrits
import com.jdiazcano.kota.model.toTrytes

fun Int.timesDo(operation: () -> Unit) {
    for (i in 0 until this) {
        operation()
    }
}

fun Int.timesDoIndexed(operation: (Int) -> Unit) {
    for (i in 0 until this) {
        operation(i)
    }
}

fun <T> T.cast(type: String, validation: (T) -> Boolean) =
        if (validation(this)) this else throw IllegalArgumentException("Invalid $type: $this")

fun Transaction.toTrytes(): String {
    return buildString {
        append(signatureMessageFragment)
        append(address.removeChecksum())
        append(value.toTrits(81).toTrytes())
        append(obsoleteTag)
        append(timestamp.toTrits(27).toTrytes())
        append(currentIndex.toTrits(27).toTrytes())
        append(this@toTrytes.lastIndex.toTrits(27).toTrytes())
        append(bundle)
        append(trunkTransaction)
        append(branchTransaction)
        append(if (attachmentTag.isEmpty()) obsoleteTag else attachmentTag)
        append(attachmentTimestamp.toTrits(27).toTrytes())
        append(attachmentTimestampLowerBound.toTrits(27).toTrytes())
        append(attachmentTimestampUpperBound.toTrits(27).toTrytes())
        append(nonce)
    }
}