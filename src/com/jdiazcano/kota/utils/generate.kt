package com.jdiazcano.kota.utils

import com.jdiazcano.kota.model.*
import com.jdiazcano.kota.pow.ICurl
import com.jdiazcano.kota.pow.JCurl.HASH_LENGTH

fun ICurl.generateAddress(seed: Seed, level: SecurityLevel, index: Int, checksum: Boolean): Address {
    val trits = seed.toTrits()
    val key = trits.key(index, level)
    val digests = digest(key, level)

    val addressArray = TritArray(HASH_LENGTH)
    val addressTrits = reset().absorb(digests).squeeze(addressArray)

    val addressString = addressTrits.toTrytes()

    return if (checksum) {
        addChecksum(addressString)
    } else {
        addressString
    }.toAddress()
}

internal fun <T> ICurl.curl(operation: () -> T): T {
    reset()
    return operation()
}

fun ICurl.addChecksum(address: String) = curl {
    absorb(address.toTrits())
    val checksumTrits = TritArray(HASH_LENGTH)
    squeeze(checksumTrits)

    buildString {
        append(address)
        append(checksumTrits.toTrytes().substring(72, 81))
    }
}

fun ICurl.digest(key: TritArray, level: SecurityLevel): TritArray {
    val digests = TritArray(level * HASH_LENGTH_TRITS)
    val keyFragment = TritArray(KEY_LENGTH)

    for (i in 0 until level) {
        System.arraycopy(key, i * KEY_LENGTH, keyFragment, 0, KEY_LENGTH)

        for (j in 0 until 27) {
            for (k in 0 until 26) {
                reset()
                        .absorb(keyFragment, j * HASH_LENGTH, HASH_LENGTH)
                        .squeeze(keyFragment, j * HASH_LENGTH, HASH_LENGTH)
            }
        }

        curl {
            absorb(keyFragment, 0, keyFragment.size)
            squeeze(digests, i * HASH_LENGTH, HASH_LENGTH)
        }
    }

    return digests
}