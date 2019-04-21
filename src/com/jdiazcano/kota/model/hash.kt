package com.jdiazcano.kota.model

import com.jdiazcano.kota.utils.ADDRESS_LENGTH_WITHOUT_CHECKSUM
import com.jdiazcano.kota.utils.ADDRESS_LENGTH_WITH_CHECKSUM

typealias Hash = String

fun String.toHash(): Hash = if (isHash(this)) this else throw IllegalArgumentException("Invalid hash: $this")

private fun isHash(hash: String): Boolean {
    if (hash.length == ADDRESS_LENGTH_WITH_CHECKSUM && !hash.isTrytes(ADDRESS_LENGTH_WITH_CHECKSUM)) {
        return false
    } else if (!hash.isTrytes(ADDRESS_LENGTH_WITHOUT_CHECKSUM)) {
        return false
    }

    return true
}

typealias Hashes = List<String>

fun List<String>.toHashes(): Hashes = if (isHashes(this)) this else throw IllegalArgumentException("Invalid hashes: $this")

fun isHashes(hashes: List<String>) = hashes.any { !isHash(it) }