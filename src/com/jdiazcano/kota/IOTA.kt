package com.jdiazcano.kota

import com.jdiazcano.kota.model.toSecurityLevel
import com.jdiazcano.kota.model.toSeed
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.runBlocking

@KtorExperimentalAPI
fun main() {

    val service = IotaService()
    val seed = "MQYJIVTGXVM9FQOYHL99XXHWQINASDOVLYOZWMZUJJORNKZZBCGLWTVBSU9WVVEIYDHFJQIQBWFIGJDOW".toSeed()//generateNewSeed()

    runBlocking {
        val address = service.generateNewAddresses(seed, 1.toSecurityLevel(), false, 0, 10L, false).addresses.first()
        val response = service.getNodeInfo()
        println(response)
    }
}