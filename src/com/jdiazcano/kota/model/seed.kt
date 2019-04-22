package com.jdiazcano.kota.model

import com.jdiazcano.kota.utils.SEED_LENGTH_MAX
import com.jdiazcano.kota.utils.cast

typealias Seed = String

fun String.toSeed(): Seed = cast("seed", ::isValidSeed)

private fun isValidSeed(seed: String): Boolean {
    return seed.length <= SEED_LENGTH_MAX && seed.isTrytes()
}