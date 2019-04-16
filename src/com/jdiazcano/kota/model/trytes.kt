package com.jdiazcano.kota.model

import com.jdiazcano.kota.utils.TRYTE_ALPHABET

object Converter {
    const val HIGH_INTEGER_BITS = -0x1
    const val HIGH_LONG_BITS = -0x1L
}

/**
 * Conversion of ASCII encoded bytes to trytes.
 * Input is a string (can be stringified JSON object), return value is Trytes
 *
 * How the conversion works:
 * 2 Trytes === 1 Byte
 * There are a total of 27 different tryte values: 9ABCDEFGHIJKLMNOPQRSTUVWXYZ
 *
 * 1. We get the decimal value of an individual ASCII character
 * 2. From the decimal value, we then derive the two tryte values by basically calculating the tryte equivalent (e.g. 100 === 19 + 3 * 27)
 * a. The first tryte value is the decimal value modulo 27 (27 trytes)
 * b. The second value is the remainder (decimal value - first value), divided by 27
 * 3. The two values returned from Step 2. are then input as indices into the available values list ('9ABCDEFGHIJKLMNOPQRSTUVWXYZ') to get the correct tryte value
 *
 *
 * EXAMPLE
 *
 * Lets say we want to convert the ASCII character "Z".
 * 1. 'Z' has a decimal value of 90.
 * 2. 90 can be represented as 9 + 3 * 27. To make it simpler:
 * a. First value: 90 modulo 27 is 9. This is now our first value
 * b. Second value: (90 - 9) / 27 is 3. This is our second value.
 * 3. Our two values are now 9 and 3. To get the tryte value now we simply insert it as indices into '9ABCDEFGHIJKLMNOPQRSTUVWXYZ'
 * a. The first tryte value is '9ABCDEFGHIJKLMNOPQRSTUVWXYZ'[9] === "I"
 * b. The second tryte value is '9ABCDEFGHIJKLMNOPQRSTUVWXYZ'[3] === "C"
 * Our tryte pair is "IC"
 *
 * @param inputString The input String.
 * @return The ASCII char "Z" is represented as "IC" in trytes.
 */
fun String.toTrytes(): String {
    return buildString {
        this@toTrytes.forEach { charStr ->
            val char = charStr.toInt().let { if (it > 255) 32 else it }

            val first = char % 27
            val second = (char - first) / 27

            append(TRYTE_ALPHABET[first])
            append(TRYTE_ALPHABET[second])
        }
    }
}

/**
 * Converts Trytes of even length to an ASCII string.
 * Reverse operation from the asciiToTrytes
 * 2 Trytes == 1 Byte
 *
 * @param inputTrytes the trytes we want to convert
 * @return an ASCII string or null when the inputTrytes are uneven
 *
 * @throws ArgumentException When the trytes in the string are an odd number
 */
fun String.fromTryes(): String {
    if (length % 2 != 0) {
        throw IllegalArgumentException("Odd amount of trytes supplied")
    }

    return buildString {
        for (index in 0 until this@fromTryes.length step 2) {
            val first = TRYTE_ALPHABET.indexOf(this@fromTryes[index])
            val second = TRYTE_ALPHABET.indexOf(this@fromTryes[index + 1])

            val value = first + second * 27

            append(value.toChar())
        }
    }
}