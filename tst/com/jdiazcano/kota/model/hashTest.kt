package com.jdiazcano.kota.model

import com.jdiazcano.kota.utils.ADDRESS_LENGTH_WITHOUT_CHECKSUM
import com.jdiazcano.kota.utils.ADDRESS_LENGTH_WITH_CHECKSUM
import io.kotlintest.data.forall
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class HashTest: StringSpec({

    "valid hashes" {
        forall(
                row("A".repeat(ADDRESS_LENGTH_WITHOUT_CHECKSUM)),
                row("A".repeat(ADDRESS_LENGTH_WITH_CHECKSUM))
        ) {
            it.toHash()
        }
    }

    "invalid hashes" {
        forall(
                row("a".repeat(ADDRESS_LENGTH_WITHOUT_CHECKSUM)),
                row("a".repeat(ADDRESS_LENGTH_WITH_CHECKSUM)),
                row("a".repeat(ADDRESS_LENGTH_WITH_CHECKSUM - 1) + "&"),
                row(""),
                row("3"),
                row("3".repeat(ADDRESS_LENGTH_WITHOUT_CHECKSUM)),
                row("3".repeat(ADDRESS_LENGTH_WITH_CHECKSUM)),
                row("a")
        ) {
            shouldThrow<IllegalArgumentException> {
                it.toHash()
            }
        }
    }

    "valid hashes in array" {
        arrayListOf("A".repeat(ADDRESS_LENGTH_WITHOUT_CHECKSUM), "A".repeat(ADDRESS_LENGTH_WITH_CHECKSUM)).toHashes()
    }

    "invalid hashes in array" {
        shouldThrow<IllegalArgumentException> {
            arrayListOf(
                    "a".repeat(ADDRESS_LENGTH_WITHOUT_CHECKSUM),
                    "a".repeat(ADDRESS_LENGTH_WITH_CHECKSUM),
                    "a".repeat(ADDRESS_LENGTH_WITH_CHECKSUM - 1) + "&",
                    "",
                    "3",
                    "3".repeat(ADDRESS_LENGTH_WITHOUT_CHECKSUM),
                    "3".repeat(ADDRESS_LENGTH_WITH_CHECKSUM),
                    "a"
            ).toHashes()
        }
    }

})