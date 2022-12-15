package me.akifev.sort

import org.junit.jupiter.api.Test
import kotlin.random.Random

abstract class QuickSortTest {
    abstract fun IntArray.sortArray()

    @Test
    fun testEmptyArray() {
        test(array = intArrayOf())
    }

    @Test
    fun testOneElementArray() {
        test(array = intArrayOf(1))
    }

    @Test
    fun testTwoElementArray() {
        test(array = intArrayOf(1, 1))
        test(array = intArrayOf(2, 1))
    }

    @Test
    fun testEqualElementArray() {
        test(array = IntArray(10) { 10 })
        test(array = IntArray(10) { -10 })
    }

    @Test
    fun testNegativeElementArray() {
        test(array = intArrayOf(1, 2, -2, -1, -1, 0))
    }

    @Test
    fun testRandomRepeatedElementArray() {
        test(array = IntArray(10000) { Random.nextInt(-100, 100) })
    }

    @Test
    fun testBigRandomArray() {
        test(array = IntArray(10_000_000) { Random.nextInt() })
    }

    private fun test(array: IntArray) {
        val expected = array.sortedArray()
        val actual = array.apply { sortArray() }
        check(expected.size == actual.size)
        check(expected.contentEquals(actual))
    }
}