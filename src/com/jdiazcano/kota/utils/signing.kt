package com.jdiazcano.kota.utils

import com.jdiazcano.kota.model.SecurityLevel
import com.jdiazcano.kota.model.TritArray
import com.jdiazcano.kota.model.inc
import com.jdiazcano.kota.pow.JCurl.HASH_LENGTH
import com.jdiazcano.kota.pow.SpongeMode
import com.jdiazcano.kota.pow.create

fun IntArray.subseed(index: Int): IntArray {
    return clone().apply {
        index.timesDo { inc() }
    }
}

fun TritArray.key(index: Int, securityLevel: SecurityLevel): TritArray {
    val seed = subseed(index)

    val curl = SpongeMode.KERL.create()
    curl.reset()
    curl.absorb(seed, 0, seed.size)
    curl.squeeze(seed, 0, seed.size)
    curl.reset()
    curl.absorb(seed, 0, seed.size)

    val key = IntArray(securityLevel * HASH_LENGTH * 27)
    val buffer = IntArray(seed.size)
    var offset = 0

    securityLevel.timesDo {
        27.timesDo {
            curl.squeeze(buffer, 0, seed.size)
            buffer.copyInto(key, offset, 0, HASH_LENGTH)

            offset += HASH_LENGTH
        }
    }

    return key
}