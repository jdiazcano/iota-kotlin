package com.jdiazcano.kota.model

import com.jdiazcano.kota.utils.TAG_LENGTH
import com.jdiazcano.kota.utils.cast

typealias Tag = String

fun String.toTag(): Tag = cast("tag", ::isValidTag)

private fun isValidTag(tag: String): Boolean {
    return tag.length < TAG_LENGTH && tag.isTrytes()
}