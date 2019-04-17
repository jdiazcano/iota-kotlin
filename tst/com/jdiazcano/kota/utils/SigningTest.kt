package com.jdiazcano.kota.utils

import com.jdiazcano.kota.model.*
import com.jdiazcano.kota.pow.SpongeMode
import com.jdiazcano.kota.pow.create
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
        scenario("create a simple key") {
            val expected = SigningTest::class.java.getResource("/signing.key").readText().split("\\s+".toRegex()).map { it.toInt().toTrit() }.toIntArray()
            val seed = "9GRAUQMMFQLEEUTCFFJPGRPFRKVVPZNSKMHPMEQXWMPNNSIYSXWNPXVALQKYIEEPAULECITUGTABHWXUV".toTrits()
            val key = seed.key(0, 1.toSecurityLevel())
            key shouldBe expected
        }
    }

    feature("address generation") {
        val curl = SpongeMode.KERL.create()
        val seed = "IHDEENZYITYVYSPKAURUZAQKGVJEREFDJMYTANNXXGPZ9GJWTEOJJ9IPMXOGZNQLSNMFDSQOTZAEETUEA".toSeed()
        val ADDR_SEED = "LIESNFZLPFNWAPWXBLKEABZEEWUDCXKTRKZIRTPCKLKWOMJSEREWKMMMODUOFWM9ELEVXADTSQWMSNFVD".toSeed()

        scenario("generate an address first index") {
            val firstAddress = "LXQHWNY9CQOHPNMKFJFIJHGEPAENAOVFRDIBF99PPHDTWJDCGHLYETXT9NPUVSNKT9XDTDYNJKJCPQMZCCOZVXMTXC"

            curl.generateAddress(seed, 2.toSecurityLevel(), 0, true) shouldBe firstAddress
        }

        scenario("generate an address fifth index") {
            val sixthAddress = "HLHRSJNPUUGRYOVYPSTEQJKETXNXDIWQURLTYDBJADGIYZCFXZTTFSOCECPPPPY9BYWPODZOCWJKXEWXDPUYEOTFQA"

            curl.generateAddress(seed, 2.toSecurityLevel(), 5, true) shouldBe sixthAddress
        }

        scenario("generate different with different security levels") {
            val ADDR_I0_S1 = "HIPPOUPZFMHJUQBLBVWORCNJWAOSFLHDWF9IOFEYVHPTTAAF9NIBMRKBICAPHYCDKMEEOXOYHJBMONJ9D"
            val ADDR_I0_S2 = "BPYZABTUMEIOARZTMCDNUDAPUOFCGKNGJWUGUXUKNNBVKQARCZIXFVBZAAMDAFRS9YOIXWOTEUNSXVOG9"
            val ADDR_I0_S3 = "BYWHJJYSHSEGVZKKYTJTYILLEYBSIDLSPXDLDZSWQ9XTTRLOSCBCQ9TKXJYQAVASYCMUCWXZHJYRGDOBW"

            curl.generateAddress(ADDR_SEED, 1.toSecurityLevel(), 0, false) shouldBe ADDR_I0_S1
            curl.generateAddress(ADDR_SEED, 2.toSecurityLevel(), 0, false) shouldBe ADDR_I0_S2
            curl.generateAddress(ADDR_SEED, 3.toSecurityLevel(), 0, false) shouldBe ADDR_I0_S3
        }

        scenario("generate different with lower seeds") {
            val ADDR_LS_I0_S1 = "VKPCVHWKSCYQNHULMPYDZTNKOQHZNPEGJVPEHPTDIUYUBFKFICDRLLSIULHCVHOHZRHJOHNASOFRWFWZC"
            val ADDR_LS_I0_S2 = "PTHVACKMXOKIERJOFSRPBWCNKVEXQ9CWUTIJGEUORSKWEDDJCBFQCCBQZLTYXQCXEDWLTMRQM9OQPUGNC"
            val ADDR_LS_I0_S3 = "AGSAAETPMSBCDOSNXFXIOBAE9MVEJCSWVP9PAULQ9VABOTWLDMXID9MXCCWQIWRTJBASWPIJDFUC9ISWD"

            curl.generateAddress(ADDR_SEED + ADDR_SEED, 1.toSecurityLevel(), 0, false) shouldBe ADDR_LS_I0_S1
            curl.generateAddress(ADDR_SEED + ADDR_SEED, 2.toSecurityLevel(), 0, false) shouldBe ADDR_LS_I0_S2
            curl.generateAddress(ADDR_SEED + ADDR_SEED, 3.toSecurityLevel(), 0, false) shouldBe ADDR_LS_I0_S3
        }
    }
})
