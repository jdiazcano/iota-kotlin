package com.jdiazcano.kota.utils

import com.jdiazcano.kota.model.Seed
import com.jdiazcano.kota.model.toSeed
import java.security.SecureRandom

fun generateNewSeed(): Seed {
    val random = SecureRandom()
    return buildString {
        SEED_LENGTH_MAX.timesDo {
            append(TRYTE_ALPHABET[random.nextInt(TRYTE_ALPHABET.length)])
        }
    }.toSeed()
}