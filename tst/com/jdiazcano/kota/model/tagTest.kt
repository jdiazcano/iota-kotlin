package com.jdiazcano.kota.model

import io.kotlintest.data.forall
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

class TagTest: StringSpec({

    forall(
            row(""),
            row("A".repeat(26)),
            row("9".repeat(26))
    ) {
        "valid tags: $it" {
            it.toTag()
        }
    }

    forall(
            row("3".repeat(27)),
            row("a".repeat(26)),
            row("A".repeat(25) + "#"),
            row("A".repeat(25) + "3")
    ) {
        "invalid tags: $it" {
            shouldThrow<IllegalArgumentException> {
                it.toTag()
            }
        }
    }

})