package com.jdiazcano.kota.utils

import com.jdiazcano.kota.model.fromTryes
import com.jdiazcano.kota.model.toTrytes
import io.kotlintest.properties.assertAll
import io.kotlintest.shouldBe
import io.kotlintest.shouldThrow
import io.kotlintest.specs.StringSpec

class ConverterTest: StringSpec({

    "converts string to trytes" {
        "Z".toTrytes() shouldBe "IC"
        "JOTA JOTA".toTrytes() shouldBe "TBYBCCKBEATBYBCCKB"
    }

    "converts trytes to string" {
        "IC".fromTryes() shouldBe "Z"
        "TBYBCCKBEATBYBCCKB".fromTryes() shouldBe "JOTA JOTA"
    }

    "converts back and forth" {
        assertAll(500) { str: String ->
            str.toTrytes().fromTryes() shouldBe str
        }
    }

    "an invalid string throws an exception" {
        shouldThrow<IllegalArgumentException> {
            "A".fromTryes()
        }
    }

})