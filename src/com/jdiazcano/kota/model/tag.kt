package com.jdiazcano.kota.model

import com.jdiazcano.kota.utils.TAG_LENGTH
import com.jdiazcano.kota.utils.isTrytes

inline class Tag(val trytes: String)

fun String.toTag() = if (isValidTag(this)) Tag(this) else throw IllegalArgumentException("Tag must be valid: $this")

private fun isValidTag(tag: String): Boolean {
    return tag.length < TAG_LENGTH && tag.isTrytes()
}