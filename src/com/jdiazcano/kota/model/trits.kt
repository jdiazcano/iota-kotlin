package com.jdiazcano.kota.model

import com.jdiazcano.kota.utils.TRYTE_ALPHABET
import com.jdiazcano.kota.utils.timesDoIndexed
import kotlin.math.absoluteValue
import kotlin.math.min

typealias Trit = Int

val RADIX = 3
val MAX_TRIT_VALUE = 1
val MIN_TRIT_VALUE = -1

private val tritsRange = MIN_TRIT_VALUE..MAX_TRIT_VALUE

fun Int.isTrit() = this in tritsRange

operator fun Trit.next() = if (this == MAX_TRIT_VALUE) MIN_TRIT_VALUE else this+1

fun Int.toTrit(): Trit = if (isTrit()) this else throw IllegalArgumentException("Trit must be valid: $this")

typealias TritArray = IntArray

fun tritArrayOf(vararg trits: Trit): TritArray {
    val array = TritArray(trits.size)
    trits.forEachIndexed { i, item -> array[i] = item }
    return array
}

internal val BYTE_TO_TRITS_MAPPINGS by lazy {
    val trits = IntArray(5)
    val value = Array(243) { IntArray(5) }
    for (i in 0 until 243) {
        value[i] = trits.copyOf()
        trits.inc()
    }
    value
}

internal val TRYTE_TO_TRITS_MAPPINGS by lazy {
    val trits = IntArray(5)
    val value = Array(27) { IntArray(5) }
    for (i in 0 until 27) {
        value[i] = trits.copyOf()
        trits.inc()
    }
    value
}

fun TritArray.inc() {
    for (j in 0 until size + 1) {
        set(j, get(j).next())

        if (get(j) != -1) {
            break
        }
    }
}

fun List<Int>.toTritArray(desiredLength: Int = size): TritArray {
    val array = TritArray(desiredLength)
    min(desiredLength, size).timesDoIndexed { index -> array[index] = get(index) }
    return array
}

fun Long.toTrits(arrayLength: Int): TritArray {
    val trits = arrayListOf<Int>()
    var absoluteValue = absoluteValue

    var position = 0

    while (absoluteValue > 0) {
        var remainder = (absoluteValue % RADIX).toInt()
        absoluteValue /= RADIX.toLong()

        if (remainder > MAX_TRIT_VALUE) {
            remainder = MIN_TRIT_VALUE
            absoluteValue++
        }

        trits.add(position++, remainder)
    }
    if (this < 0) {
        for (i in trits.indices) {
            trits[i] = -trits[i]
        }
    }
    return trits.toTritArray(arrayLength)
}

fun String.toTrits(): TritArray {
    val array = IntArray(3 * length)
    for (i in 0 until length) {
        TRYTE_TO_TRITS_MAPPINGS[TRYTE_ALPHABET.indexOf(this[i])].copyInto(array, i * 3, 0, 3)
    }
    return array
}