package com.jdiazcano.kota

import com.jdiazcano.kota.dto.request.*
import com.jdiazcano.kota.dto.response.*
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.json.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.http.HttpMethod
import io.ktor.http.URLProtocol

interface IotaServiceConfiguration {
    val host: String get() = "localhost"
    val port: Int get() = 14265
    val protocol: URLProtocol get() = URLProtocol.HTTP
}

class IotaServiceConfigImpl: IotaServiceConfiguration

class IotaService(
        val config: IotaServiceConfiguration = IotaServiceConfigImpl()
) {
    private val client = HttpClient(CIO) {
        defaultRequest {
            url {
                protocol = config.protocol
                host = config.host
                port = config.port
            }
            method = HttpMethod.Post

            header("Content-Type", "application/json")
            header("X-IOTA-API-Version", "1.0")
            header("User-Agent", "KOTA-API wrapper")
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = GsonSerializer()
        }
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