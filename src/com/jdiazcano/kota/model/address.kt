package com.jdiazcano.kota.model

import com.jdiazcano.kota.utils.ADDRESS_LENGTH_WITHOUT_CHECKSUM
import com.jdiazcano.kota.utils.ADDRESS_LENGTH_WITH_CHECKSUM

typealias Address = String

fun String.toAddress(): Seed = if (isValidAddress(this)) this else throw IllegalArgumentException("Address must be valid: $this")

private fun isValidAddress(address: String): Boolean {
    return when (address.length) {
        ADDRESS_LENGTH_WITH_CHECKSUM -> address.isTrytes(ADDRESS_LENGTH_WITH_CHECKSUM)
        ADDRESS_LENGTH_WITHOUT_CHECKSUM -> address.isTrytes(ADDRESS_LENGTH_WITHOUT_CHECKSUM)
        else -> false
    }
}

fun Address.removeChecksum(): Address = substring(0, ADDRESS_LENGTH_WITHOUT_CHECKSUM)