package com.jdiazcano.kota

import com.jdiazcano.kota.dto.request.IotaAttachToTangleRequest
import com.jdiazcano.kota.dto.request.IotaGetTransactionsToApproveRequest
import com.jdiazcano.kota.model.toSeed
import com.jdiazcano.kota.model.toTrytes
import io.ktor.http.URLProtocol
import io.ktor.util.KtorExperimentalAPI
import kotlinx.coroutines.runBlocking

@KtorExperimentalAPI
fun main() {

    val service = IotaService(object : IotaServiceConfiguration {
        override val protocol = URLProtocol.HTTPS
        override val host = "nodes.thetangle.org"
        override val port = 443
    })
    val seed = "MQYJIVTGXVM9FQOYHL99XXHWQINASDOVLYOZWMZUJJORNKZZBCGLWTVBSU9WVVEIYDHFJQIQBWFRANDOM".toSeed()//generateNewSeed()
    val data = "LUL".toTrytes()

    runBlocking {
        val response = service.getNodeInfo()
        val transactions = service.getTransactionsToApprove(IotaGetTransactionsToApproveRequest(1))
        println(transactions)
        val attach = IotaAttachToTangleRequest(transactions.trunkTransaction, transactions.branchTransaction, 14, arrayListOf(data))
        println(service.attachToTangle(attach))
//        val address = service.generateNewAddresses(seed, 1.toSecurityLevel(), false, 0, 10L, false).addresses.first()
//        println(response)
    }
}