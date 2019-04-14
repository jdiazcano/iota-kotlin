package com.jdiazcano.kota.utils

/**
 * Empty hash, same as TVM.NULL_HASH in IRI
 */
const val NULL_HASH = "999999999999999999999999999999999999999999999999999999999999999999999999999999999"

/**
 * This String contains all possible characters of the tryte alphabet
 */
const val TRYTE_ALPHABET = "9ABCDEFGHIJKLMNOPQRSTUVWXYZ"

/**
 * The length of an IOTA seed
 */
const val SEED_LENGTH_MAX = 81

/**
 * The length of an address without checksum
 */
const val ADDRESS_LENGTH_WITHOUT_CHECKSUM = 81

/**
 * The length of a hash in trits
 */
const val HASH_LENGTH_TRITS = 243

/**
 * The length of an address with checksum
 */
const val ADDRESS_LENGTH_WITH_CHECKSUM = 90

/**
 * The length of an message
 */
const val MESSAGE_LENGTH = 2187

/**
 * The length of a transaction
 */
const val TRANSACTION_LENGTH = 2673

/**
 * Maximum number represented in 27 trits
 */
const val TRANSACTION_UPPER_BOUND_MAX = 3_812_798_742_493L

/**
 * The length of an tag in trytes
 */
const val TAG_LENGTH = 27

/**
 * Maximum security level of an address
 */
const val MIN_SECURITY_LEVEL = 1

/**
 * Minimum security level of an address
 */
const val MAX_SECURITY_LEVEL = 3

/**
 * Length of a single level of security for the trits of a signing key
 */
const val KEY_LENGTH = 6561


const val ARRAY_NULL_OR_EMPTY = "Array cannot be null or empty"

const val INVALID_THRESHOLD_ERROR = "Invalid threshold provided. (Between 0 and 100 incl.)"
const val INVALID_APPROVE_DEPTH_ERROR = "Invalid depth provided. (Between 0 and 15, soft upper bound)"

const val INVALID_TAG_INPUT_ERROR = "Invalid tag provided."
const val INVALID_TRYTES_INPUT_ERROR = "Invalid trytes provided."
const val INVALID_TRITS_INPUT_ERROR = "Invalid trits provided."
const val INVALID_HASH_INPUT_ERROR = "Invalid hash provided."
const val INVALID_HASHES_INPUT_ERROR = "Invalid hashes provided."
const val INVALID_TAIL_HASH_INPUT_ERROR = "Invalid tail hash provided."
const val INVALID_SEED_INPUT_ERROR = "Invalid seed provided."
const val INVALID_SECURITY_LEVEL_INPUT_ERROR = "Invalid security level provided."
const val INVALID_ATTACHED_TRYTES_INPUT_ERROR = "Invalid attached trytes provided."
const val INVALID_TRANSFERS_INPUT_ERROR = "Invalid transfers provided."
const val INVALID_ADDRESS_INPUT_ERROR = "Invalid address provided."
const val INVALID_ADDRESSES_INPUT_ERROR = "Invalid addresses provided."
const val INVALID_INPUT_ERROR = "Invalid input provided."
const val INVALID_INDEX_INPUT_ERROR = "Invalid index provided."

const val INVALID_BUNDLE_ERROR = "Invalid bundle."
const val INVALID_BUNDLE_SUM_ERROR = "Invalid bundle sum."
const val INVALID_BUNDLE_HASH_ERROR = "Invalid bundle hash."
const val INVALID_SIGNATURES_ERROR = "Invalid signatures."
const val INVALID_VALUE_TRANSFER_ERROR = "Invalid value transfer: the transfer does not require a signature."

const val NOT_ENOUGH_BALANCE_ERROR = "Not enough balance."
const val NO_REMAINDER_ADDRESS_ERROR = "No remainder address defined."

const val TRANSACTION_NOT_FOUND = "Transaction was not found on the node"

const val GET_TRYTES_RESPONSE_ERROR = "Get trytes response was null."
const val GET_BUNDLE_RESPONSE_ERROR = "Get bundle response was null."
const val GET_INCLUSION_STATE_RESPONSE_ERROR = "Get inclusion state response was null."

const val SENDING_TO_USED_ADDRESS_ERROR = "Sending to a used address."
const val PRIVATE_KEY_REUSE_ERROR = "Private key reuse detect!"
const val SEND_TO_INPUTS_ERROR = "Send to inputs!"

const val ACCOUNT_MESSAGE = "IOTA Accounts Transfer"
