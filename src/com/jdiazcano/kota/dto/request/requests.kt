package com.jdiazcano.kota.dto.request

import com.jdiazcano.kota.IotaAPICommand

interface IotaCommandRequest {
    val command: IotaAPICommand
}

data class IotaAttachToTangleRequest(val trunkTransaction: String,
                                val branchTransaction: String,
                                val minWeightMagnitude: Int,
                                val trytes: List<String>): IotaCommandRequest {
    override val command = IotaAPICommand.ATTACH_TO_TANGLE
}

data class IotaBroadcastTransactionRequest(val trytes: List<String>): IotaCommandRequest {
    override val command = IotaAPICommand.BROADCAST_TRANSACTIONS
}

data class IotaCheckConsistencyRequest(val tails: List<String>): IotaCommandRequest {
    override val command = IotaAPICommand.CHECK_CONSISTENCY
}

data class IotaFindTransactionsRequest(
        val addresses: List<String>,
        val bundles: List<String>? = null,
        val tags: List<String>? = null,
        val approvees: List<String>? = null
): IotaCommandRequest {
    override val command = IotaAPICommand.FIND_TRANSACTIONS
}

data class IotaGetBalancesRequest(
        val transactions: List<String>,
        val tips: List<String>
): IotaCommandRequest {
    override val command = IotaAPICommand.GET_BALANCES
}

data class IotaGetInclusionStateRequest(
        val addresses: List<String>,
        val threshold: Int,
        val tips: List<String>
): IotaCommandRequest {
    override val command = IotaAPICommand.GET_INCLUSIONS_STATES
}

data class IotaGetTransactionsToApproveRequest(
        val depth: Int,
        val reference: String? = null
): IotaCommandRequest {
    override val command = IotaAPICommand.GET_TRANSACTIONS_TO_APPROVE
}

data class IotaGetTrytesRequest(val hashes: List<String>): IotaCommandRequest {
    override val command = IotaAPICommand.GET_TRYTES
}

data class IotaAddNeighborsRequest(val uris: List<String>): IotaCommandRequest {
    override val command = IotaAPICommand.ADD_NEIGHBORS
}

class IotaGetNeighborsRequest: IotaCommandRequest {
    override val command = IotaAPICommand.GET_NEIGHBORS
}

data class IotaRemoveNeighborsRequest(val uris: List<String>): IotaCommandRequest {
    override val command = IotaAPICommand.REMOVE_NEIGHBORS
}

data class IotaStoreTransactionsRequest(val trytes: List<String>): IotaCommandRequest {
    override val command = IotaAPICommand.STORE_TRANSACTIONS
}

data class IotaWereAddressesSpentFromRequest(val addresses: List<String>): IotaCommandRequest {
    override val command = IotaAPICommand.WERE_ADDRESSES_SPENT_FROM
}

class IotaNodeInfoRequest: IotaCommandRequest {
    override val command = IotaAPICommand.GET_NODE_INFO
}

class IotaGetTipsRequest: IotaCommandRequest {
    override val command = IotaAPICommand.GET_TIPS
}

class IotaInterruptAttachToTangleRequest: IotaCommandRequest {
    override val command = IotaAPICommand.INTERRUPT_ATTACHING_TO_TANGLE
}

