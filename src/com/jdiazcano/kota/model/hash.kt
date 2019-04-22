package com.jdiazcano.kota.model

import com.jdiazcano.kota.utils.ADDRESS_LENGTH_WITHOUT_CHECKSUM
import com.jdiazcano.kota.utils.ADDRESS_LENGTH_WITH_CHECKSUM
import com.jdiazcano.kota.utils.cast

typealias Hash = String

fun String.toHash(): Hash = cast("hash", ::isValidHash)

private fun isValidHash(hash: String): Boolean {
    if (hash.length == ADDRESS_LENGTH_WITH_CHECKSUM) {
        if (!hash.isTrytes(ADDRESS_LENGTH_WITH_CHECKSUM)) {
            return false
        }
    } else if (!hash.isTrytes(ADDRESS_LENGTH_WITHOUT_CHECKSUM)) {
        return false
    }

    return true
}

typealias Hashes = List<String>

fun List<String>.toHashes(): Hashes = cast("hashes", ::isValidHashes)

private fun isValidHashes(hashes: List<String>) = hashes.all { isValidHash(it) }