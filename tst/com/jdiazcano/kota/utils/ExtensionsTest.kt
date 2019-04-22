package com.jdiazcano.kota.utils

import com.jdiazcano.kota.model.Transaction
import com.jdiazcano.kota.model.toAddress
import com.jdiazcano.kota.model.toHash
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ExtensionsTest: StringSpec({

    "transaction is converted to trytes correctly" {
        val transaction = Transaction(
                "A".repeat(81).toHash(),
                "XVWIO9XRAHKTUBHMAWEKCJCAZCTCAKQAIEMUH9RTIQTLMRAALSPZHNAJXRQXTYZGXXDGBFBKPLZQPKZFC".toAddress(),
                0,
                "OBSOLETETAG",
                11111111L,
                1,
                1,
                "BUNDLE",
                "TRUNKTRANSACTION",
                "BRANCHTRANSACTION",
                "NONCE",
                false,
                11111112L,
                "TAG",
                11111111L,
                11111113L,
                "AAAAAAAAAAAAAAA"
        )
        transaction.toTrytes() shouldBe "AAAAAAAAAAAAAAAXVWIO9XRAHKTUBHMAWEKCJCAZCTCAKQAIEMUH9RTIQTLMRAALSPZHNAJXRQXTYZGXXDGBFBKPLZQPKZFC999999999999999999999999999OBSOLETETAGQPNYUA999A99999999A99999999BUNDLETRUNKTRANSACTIONBRANCHTRANSACTIONTAGRPNYUA999QPNYUA999SPNYUA999NONCE"
    }

    "transaction is converted to trytes correctly and uses the obsolete tag if needed" {
        val transaction = Transaction(
                "A".repeat(81).toHash(),
                "XVWIO9XRAHKTUBHMAWEKCJCAZCTCAKQAIEMUH9RTIQTLMRAALSPZHNAJXRQXTYZGXXDGBFBKPLZQPKZFC".toAddress(),
                0,
                "OBSOLETETAG",
                11111111L,
                1,
                1,
                "BUNDLE",
                "TRUNKTRANSACTION",
                "BRANCHTRANSACTION",
                "NONCE",
                false,
                11111112L,
                "",
                11111111L,
                11111113L,
                "AAAAAAAAAAAAAAA"
        )
        transaction.toTrytes() shouldBe "AAAAAAAAAAAAAAAXVWIO9XRAHKTUBHMAWEKCJCAZCTCAKQAIEMUH9RTIQTLMRAALSPZHNAJXRQXTYZGXXDGBFBKPLZQPKZFC999999999999999999999999999OBSOLETETAGQPNYUA999A99999999A99999999BUNDLETRUNKTRANSACTIONBRANCHTRANSACTIONOBSOLETETAGRPNYUA999QPNYUA999SPNYUA999NONCE"
    }

})