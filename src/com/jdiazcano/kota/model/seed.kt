package com.jdiazcano.kota.model

import com.jdiazcano.kota.utils.SEED_LENGTH_MAX

typealias Seed = String

fun String.toSeed(): Seed = if (isValidSeed(this)) this else throw IllegalArgumentException("Seed must be valid: $this")

private fun isValidSeed(seed: String): Boolean {
    return seed.length <= SEED_LENGTH_MAX && seed.isTrytes()
}