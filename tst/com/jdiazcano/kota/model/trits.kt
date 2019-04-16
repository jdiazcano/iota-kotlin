package com.jdiazcano.kota.model

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class TritsTest: StringSpec({

    "only -1, 0 and 1 are valid trits" {
        (-1).toTrit() shouldBe -1
        0.toTrit() shouldBe 0
        1.toTrit() shouldBe 1
    }

    "over or under, aren't valid" {
        shouldThrow<IllegalArgumentException> {
            (-2).toTrit()
        }
        shouldThrow<IllegalArgumentException> {
            2.toTrit()
        }
    }

})