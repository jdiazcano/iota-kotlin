package com.jdiazcano.kota.model

import io.kotlintest.data.forall
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class SeedTest: StringSpec({

    forall(
            row("AAAAAAAAAA"),
            row("NPSOIUDHFPSOI99999")
    ) {
        "valid seed: $it" {
            it.toSeed()
        }
    }

    forall(
            row("a")
    ) {
        "invalid seeds: $it" {
            shouldThrow<IllegalArgumentException> {
                it.toSeed()
            }
        }
    }

})