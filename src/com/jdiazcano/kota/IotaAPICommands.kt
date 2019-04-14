package com.jdiazcano.kota

inline class IotaAPICommand(val name: String) {
    companion object {
        val GET_NODE_INFO = IotaAPICommand("getNodeInfo")
        val GET_NEIGHBORS = IotaAPICommand("getNeighbors")
        val ADD_NEIGHBORS = IotaAPICommand("addNeighbors")
        val REMOVE_NEIGHBORS = IotaAPICommand("removeNeighbors")
        val GET_TIPS = IotaAPICommand("getTips")
        val FIND_TRANSACTIONS = IotaAPICommand("findTransactions")
        val GET_TRYTES = IotaAPICommand("getTrytes")
        val GET_INCLUSIONS_STATES = IotaAPICommand("getInclusionStates")
        val GET_BALANCES = IotaAPICommand("getBalances")
        val GET_TRANSACTIONS_TO_APPROVE = IotaAPICommand("getTransactionsToApprove")
        val ATTACH_TO_TANGLE = IotaAPICommand("attachToTangle")
        val INTERRUPT_ATTACHING_TO_TANGLE = IotaAPICommand("interruptAttachingToTangle")
        val BROADCAST_TRANSACTIONS = IotaAPICommand("broadcastTransactions")
        val STORE_TRANSACTIONS = IotaAPICommand("storeTransactions")
        val CHECK_CONSISTENCY = IotaAPICommand("checkConsistency")
        val WERE_ADDRESSES_SPENT_FROM = IotaAPICommand("wereAddressesSpentFrom")
    }
}