package com.jdiazcano.kota.dto.response

import com.jdiazcano.kota.model.Input
import com.jdiazcano.kota.model.Neighbor
import com.jdiazcano.kota.model.Transaction

interface Response {
    val duration: Long
}

data class AddNeighborsResponse(
        override val duration: Long,
        val addedNeighbors: Int
): Response

data class AnalyzeTransactionResponse(
        override val duration: Long,
        val transactions: List<Transaction>
): Response

data class BroadcastTransactionsResponse(
        override val duration: Long
): Response

data class CheckConsistencyResponse(
        override val duration: Long,
        val state: Boolean,
        val info: String
): Response

data class FindTransactionResponse(
        override val duration: Long,
        val hashes: List<String>
): Response

data class GetAccountDataResponse(
        override val duration: Long,
        val addresses: List<String>,
//        val transferBundle: List<Bundle>,
        val inputs: List<Input>,
        val balance: Long
): Response

data class GetNodeInfoResponse(
        override val duration: Long,
        val appName: String,
        val appVersion: String,
        val jreAvailableProcessors: Int,
        val jreFreeMemory: Long,
        val jreVersion: String,
        val jreMaxMemory: Long,
        val jreTotalMemory: Long,
        val latestMilestone: String,
        val latestMilestoneIndex: Int,
        val latestSolidSubtangleMilestone: String,
        val latestSolidSubtangleMilestoneIndex: Int,
        val milestoneStartIndex: Int,
        val neighbors: Int,
        val packetsQueueSize: Int,
        val time: Long,
        val tips: Int,
        val transactionsToRequest: Int,
        val features: List<String>,
        val coordinatorAddress: String
): Response

data class GetNeighborsResponse(
        override val duration: Long,
        val neighbors: List<Neighbor>
): Response

data class GetTipsResponse(
        override val duration: Long,
        val hashes: List<String>
): Response

data class GetInclusionStateResponse(
        override val duration: Long,
        val states: List<Boolean>
): Response

data class GetTrytesResponse(
        override val duration: Long,
        val trytes: List<String>
): Response

data class GetTransactionsToApproveResponse(
        override val duration: Long,
        val trunkTransaction: String,
        val branchTransaction: String
): Response

data class GetBalancesResponse(
        override val duration: Long,
        val balances: List<String>,
        val references: List<String>,
        val milestoneIndex: Int
): Response

data class GetAttachToTangleResponse(
        override val duration: Long,
        val trytes: List<String>
): Response

class InterruptAttachingToTangleResponse(
        override val duration: Long
): Response

class StoreTransactionsResponse(
        override val duration: Long
): Response

class WereAddressesSpentFromResponse(
        override val duration: Long,
        val states: List<Boolean>
): Response

