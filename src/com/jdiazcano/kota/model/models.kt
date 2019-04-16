package com.jdiazcano.kota.model

data class Input(
        val address: String,
        val balance: Long,
        val keyIndex: Int,
        val security: SecurityLevel
)

data class Inputs(
        val inputsList: List<Input>,
        val totalBalance: Long
)

data class Neighbor(
        val address: String,
        val numberOfAllTransactions: Int,
        val numberOfInvalidTransactions: Int,
        val numberOfNewTransactions: Int,
        val numberOfRandomTransactionRequests: Int,
        val numberOfSentTransactions: Int,
        val connectionType: String
)

data class Signature(
        val address: String,
        val signatureFragments: List<String>
)

data class Transfer(
        val address: String,
        val value: Long,
        val hash: String? = null,
        val persistence: Boolean? = null,
        val message: String = "",
        val tag: String = "",
        val timestamp: String? = null
)

data class Transaction(
        val hash: String,
        val address: String,
        val value: Long,
        val obsoleteTag: String,
        val timestamp: Long,
        val currentIndex: Long,
        val lastIndex: Long,
        val bundle: String,
        val trunkTransaction: String,
        val branchTransaction: String,
        val nonce: String,
        val persistence: Boolean,
        val attachmentTimestamp: Long,
        val attachmentTag: String,
        val attachmentTimestampLowerBound: Long,
        val attachmentTimestampUpperBound: Long,
        val signatureMessageFragment: String
)

data class Bundle(
        val transactions: List<Transaction>,
        val length: Int

)