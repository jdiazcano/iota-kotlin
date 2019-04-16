package com.jdiazcano.kota.utils

import com.jdiazcano.kota.model.*
import io.kotlintest.shouldBe
import io.kotlintest.specs.FeatureSpec

class SigningTest: FeatureSpec({

    feature("seeds") {
        val seed0 = tritArrayOf( 0,0,0,1,0,-1,0,1,1,0,-1,1,-1,0)
        val seed1 = tritArrayOf( 1,0,0,1,0,-1,0,1,1,0,-1,1,-1,0)
        val seed2 = tritArrayOf(-1,1,0,1,0,-1,0,1,1,0,-1,1,-1,0)
        val seed3 = tritArrayOf( 0,1,0,1,0,-1,0,1,1,0,-1,1,-1,0)

        scenario("i can derive once") {
            seed0.subseed(0) shouldBe seed0
        }

        scenario("i can derive twice") {
            seed0.subseed(1) shouldBe seed1
        }

        scenario("i can derive three times") {
            seed0.subseed(2) shouldBe seed2
        }

        scenario("i can derive four times") {
            seed0.subseed(3) shouldBe seed3
        }
    }

    feature("creates keys") {
        scenario("f:i can create a simple key") {
            val expected = SigningTest::class.java.getResource("/signing.key").readText().split("\\s+".toRegex()).map { it.toInt().toTrit() }.toIntArray()
            val seed = "9GRAUQMMFQLEEUTCFFJPGRPFRKVVPZNSKMHPMEQXWMPNNSIYSXWNPXVALQKYIEEPAULECITUGTABHWXUV".toTrits()
            val key = seed.key(0, 1.toSecurityLevel())
            key shouldBe expected
        }
    }
})
