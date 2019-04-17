package com.jdiazcano.kota.model

import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class SecurityLevelTest: StringSpec({

    "only 1, 2 and 3 are valid security levels" {
        1.toSecurityLevel() shouldBe 1
        2.toSecurityLevel() shouldBe 2
        3.toSecurityLevel() shouldBe 3
    }

    "over or under, aren't valid" {
        shouldThrow<IllegalArgumentException> {
            0.toSecurityLevel()
        }
        shouldThrow<IllegalArgumentException> {
            4.toSecurityLevel()
        }
    }

})