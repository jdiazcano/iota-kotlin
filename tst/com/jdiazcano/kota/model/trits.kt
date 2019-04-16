package com.jdiazcano.kota.model

import io.kotlintest.data.forall
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row

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

    "converting to trits a seed" {
        forall(
                row("9MNTOEIVEYOPBDATEYRXWZUXRCEHZOQJAXERPTAJDFGJJZAUDCVPXJYYLKAJOQMOFCUNUEIDLKSIUWNUX", tritArrayOf(0, 0, 0, 1, 1, 1, -1, -1, -1, -1, 1, -1, 0, -1, -1, -1, -1, 1, 0, 0, 1, 1, 1, -1, -1, -1, 1, 1, -1, 0, 0, -1, -1, 1, -1, -1, -1, 1, 0, 1, 1, 0, 1, 0, 0, -1, 1, -1, -1, -1, 1, 1, -1, 0, 0, 0, -1, 0, -1, 0, -1, -1, 0, -1, 0, 0, 0, 1, -1, 0, -1, 0, 0, 0, -1, 0, 1, 0, -1, -1, 1, -1, 0, 1, -1, 0, 0, 0, -1, -1, -1, 0, -1, 1, 0, 1, 1, 0, 0, 0, -1, 0, -1, -1, 1, 0, 0, -1, 1, -1, -1, -1, 1, -1, 1, 0, 0, 1, 0, 1, 1, 1, 0, 0, -1, 1, 1, -1, 1, 1, 0, 1, 1, 0, 1, -1, 0, 0, 1, 0, 0, 0, 1, -1, 1, 1, 0, 0, 1, 0, 1, 1, -1, 1, -1, -1, 0, -1, 0, 1, 0, 1, 1, -1, 0, 1, -1, 0, 0, 1, 1, -1, 1, 1, 1, 0, 0, 1, 0, 1, 0, -1, -1, -1, 0, -1, 1, 1, 1, 0, -1, -1, 0, -1, 1, 0, 1, 0, 0, 1, -1, -1, -1, -1, 0, 1, -1, -1, -1, 1, 0, 0, 1, 1, 1, 0, 0, 1, 1, -1, 1, 1, 1, 0, -1, 0, 0, 1, 0, 1, -1, -1, -1, 0, -1, -1, -1, 0, 1, -1, 0, -1, 0)),
                row("AVPGYZWUEAGEACKN9SWHGSMONFPOYACCNNRMQWXNVIZJZMQLDMBFZRPIANGGGEPHLZULFUQEIZVPIFBEA", tritArrayOf(1, 0, 0, 1, 1, -1, 1, -1, -1, 1, -1, 1, 1, -1, 0, -1, 0, 0, -1, -1, 0, 0, 1, -1, -1, -1, 1, 1, 0, 0, 1, -1, 1, -1, -1, 1, 1, 0, 0, 0, 1, 0, -1, 1, 1, -1, -1, -1, 0, 0, 0, 1, 0, -1, -1, -1, 0, -1, 0, 1, 1, -1, 1, 1, 0, -1, 1, 1, 1, 0, -1, -1, -1, -1, -1, 0, -1, 1, 1, -1, -1, 0, -1, -1, 1, -1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, -1, -1, -1, -1, -1, -1, 0, 0, -1, 1, 1, 1, -1, 0, -1, -1, -1, 0, 0, -1, 0, -1, -1, -1, 1, 1, -1, 0, 0, 1, -1, 0, 0, 1, 0, 1, -1, 0, 0, 1, 1, 1, -1, 0, -1, 0, 1, 1, 1, 1, 0, 1, 1, 1, -1, 1, 0, 0, -1, 1, -1, 0, 0, 0, 0, -1, 1, -1, -1, 0, 0, 1, 1, 0, 0, -1, -1, -1, 1, -1, 1, 1, -1, 1, 1, -1, 1, -1, -1, 1, 1, -1, -1, -1, 0, 1, 0, 1, 1, -1, 0, 0, 0, 1, -1, 0, 1, 1, 0, -1, 1, 0, 1, -1, -1, 0, -1, -1, -1, 1, 0, 0, 1, -1, 0, 0, 1, 1, -1, 1, -1, -1, 0, 0, 1, 0, -1, 1, -1, 1, 0, -1, -1, 1, 1, 0, 0))
        ) { seed, trits ->
            seed.toTrits() shouldBe trits
        }
    }

})