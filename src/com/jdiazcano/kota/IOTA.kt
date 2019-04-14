package com.jdiazcano.kota

import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.runBlocking

@KtorExperimentalAPI
fun main() {
    val service = IotaService(IotaServiceConfiguration("localhost", 14265))

    runBlocking {
        val response = service.getNeighbors()
        println(response)
    }
}