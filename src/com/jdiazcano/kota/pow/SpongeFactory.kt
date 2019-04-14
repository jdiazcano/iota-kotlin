package com.jdiazcano.kota.pow

enum class SpongeMode {
    CURLP81,
    CURLP27,
    KERL
    ;
}

fun SpongeMode.create() = when (this) {
    SpongeMode.CURLP81 -> JCurl(this)
    SpongeMode.CURLP27 -> JCurl(this)
    SpongeMode.KERL -> Kerl()
}