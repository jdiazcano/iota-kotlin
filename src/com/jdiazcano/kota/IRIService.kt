package com.jdiazcano.kota

import com.jdiazcano.kota.dto.request.*
import com.jdiazcano.kota.dto.response.*
import com.jdiazcano.kota.model.SecurityLevel
import com.jdiazcano.kota.model.Seed
import com.jdiazcano.kota.model.removeChecksum
import com.jdiazcano.kota.pow.SpongeMode
import com.jdiazcano.kota.pow.create
import com.jdiazcano.kota.utils.generateAddress
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.GsonSerializer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol
import kotlin.system.measureTimeMillis

interface IotaServiceConfiguration {
    val host: String get() = "localhost"
    val port: Int get() = 14265
    val protocol: URLProtocol get() = URLProtocol.HTTP
}

class DefaultIotaServiceConfig: IotaServiceConfiguration

class IotaService(
        val config: IotaServiceConfiguration = DefaultIotaServiceConfig()
) {
    private val kerl = SpongeMode.KERL.create()

    private val client = HttpClient(CIO) {
        expectSuccess = false
        defaultRequest {
            url {
                protocol = config.protocol
                host = config.host
                port = config.port
            }
            method = HttpMethod.Post

            header("Content-Type", "application/json")
            header("X-IOTA-API-Version", "1.0")
//            header("User-Agent", "KOTA-API wrapper")
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
    }

    suspend fun generateNewAddresses(
            seed: Seed, security:
            SecurityLevel,
            checksum: Boolean,
            index: Int = 0,
            amount: Long = 1,
            addSpendAddresses: Boolean = false
    ): GetNewAddressResponse {
        if (index + amount < 0) {
            throw IllegalArgumentException("Balance would be less than 0.")
        }

        val allAddresses = arrayListOf<String>()
        val millis = measureTimeMillis {

            var i = index
            var numUnspentFound = 0
            while (numUnspentFound < amount) {
                val newAddress = kerl.generateAddress(seed, security, i, checksum)
                val response = findTransactions(IotaFindTransactionsRequest(
                        listOf(if (checksum) newAddress.removeChecksum() else newAddress)
                ))

                if (response.hashes.isEmpty()) {
                    //Unspent address, if we ask for 0, we dont need to add it
                    if (amount != 0L) {
                        allAddresses.add(newAddress)
                    }

                    numUnspentFound++
                } else if (addSpendAddresses) {
                    //Spend address, were interested anyways
                    allAddresses.add(newAddress)
                }
                i++
            }
        }

        return GetNewAddressResponse(millis, allAddresses)
    }

    suspend fun getNodeInfo(request: IotaNodeInfoRequest = IotaNodeInfoRequest()) =
            client.post<GetNodeInfoResponse> { body = request }

    suspend fun addNeighbors(request: IotaAddNeighborsRequest) =
            client.post<AddNeighborsResponse> { body = request }

    suspend fun getNeighbors(request: IotaGetNeighborsRequest = IotaGetNeighborsRequest()) =
            client.post<GetNeighborsResponse> { body = request }

    suspend fun removeNeighbors(request: IotaRemoveNeighborsRequest) =
            client.post<GetNodeInfoResponse> { body = request }

    suspend fun getTips(request: IotaGetTipsRequest = IotaGetTipsRequest()) =
            client.post<GetTipsResponse> { body = request }

    suspend fun findTransactions(request: IotaFindTransactionsRequest) =
            client.post<FindTransactionResponse> { body = request }

    suspend fun getInclusionStates(request: IotaGetInclusionStateRequest) =
            client.post<GetInclusionStateResponse> { body = request }

    suspend fun getTrytes(request: IotaGetTrytesRequest) =
            client.post<GetTrytesResponse> { body = request }

    suspend fun getTransactionsToApprove(request: IotaGetTransactionsToApproveRequest) =
            client.post<GetTransactionsToApproveResponse> { body = request }

    suspend fun getBalances(request: IotaGetBalancesRequest) =
            client.post<GetBalancesResponse> { body = request }

    suspend fun attachToTangle(request: IotaAttachToTangleRequest) =
            client.post<GetAttachToTangleResponse> { body = request }

    suspend fun interruptAttachingToTangle(request: IotaInterruptAttachToTangleRequest = IotaInterruptAttachToTangleRequest()) =
            client.post<InterruptAttachingToTangleResponse> { body = request }

    suspend fun broadcastTransactions(request: IotaBroadcastTransactionRequest) =
            client.post<BroadcastTransactionsResponse> { body = request }

    suspend fun storeTransactions(request: IotaStoreTransactionsRequest) =
            client.post<StoreTransactionsResponse> { body = request }

    suspend fun checkConsistency(request: IotaCheckConsistencyRequest) =
            client.post<CheckConsistencyResponse> { body = request }

    suspend fun wereAddressesSpentFrom(request: IotaWereAddressesSpentFromRequest) =
            client.post<WereAddressesSpentFromResponse> { body = request }

}