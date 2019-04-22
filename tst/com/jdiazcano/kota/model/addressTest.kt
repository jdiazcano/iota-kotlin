package com.jdiazcano.kota.model

import com.jdiazcano.kota.utils.ADDRESS_LENGTH_WITHOUT_CHECKSUM
import com.jdiazcano.kota.utils.ADDRESS_LENGTH_WITH_CHECKSUM
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class AddressTest: StringSpec({

    "valid address without checksum" {
        val address = "A".repeat(ADDRESS_LENGTH_WITHOUT_CHECKSUM)
        address.toAddress()
    }

    "valid address with checksum" {
        val address = "A".repeat(ADDRESS_LENGTH_WITH_CHECKSUM)
        address.toAddress()
    }

    "invalid address throws" {
        val address = "A".repeat(ADDRESS_LENGTH_WITHOUT_CHECKSUM + 1)
        shouldThrow<IllegalArgumentException> {
            address.toAddress()
        }
    }

})