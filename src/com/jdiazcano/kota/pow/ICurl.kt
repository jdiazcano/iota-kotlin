package com.jdiazcano.kota.pow

/**
 * This interface abstracts the curl hashing algorithm.
 *
 * @author Adrian
 */
interface ICurl {

    var state: IntArray

    /**
     * Absorbs the specified trits.
     *
     * @param trits  The trits.
     * @param offset The offset to start from.
     * @param length The length.
     * @return The ICurl instance (used for method chaining).
     */
    fun absorb(trits: IntArray, offset: Int, length: Int): ICurl

    /**
     * Absorbs the specified trits.
     *
     * @param trits The trits.
     * @return The ICurl instance (used for method chaining).
     */
    fun absorb(trits: IntArray): ICurl

    /**
     * Squeezes the specified trits.
     *
     * @param trits  The trits.
     * @param offset The offset to start from.
     * @param length The length.
     * @return The squeezed trits.
     */
    fun squeeze(trits: IntArray, offset: Int, length: Int): IntArray

    /**
     * Squeezes the specified trits.
     *
     * @param trits The trits.
     * @return The squeezed trits.
     */
    fun squeeze(trits: IntArray): IntArray

    /**
     * Transforms this instance.
     *
     * @return The ICurl instance (used for method chaining).
     */
    fun transform(): ICurl

    /**
     * Resets this state.
     *
     * @return The ICurl instance (used for method chaining).
     */
    fun reset(): ICurl

    /**
     * Clones this instance.
     *
     * @return A new instance.
     */
    fun clone(): ICurl
}
