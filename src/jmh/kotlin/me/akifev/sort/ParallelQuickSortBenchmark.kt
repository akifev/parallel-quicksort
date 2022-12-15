package me.akifev.sort

import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Param

@Suppress("unused")
open class ParallelQuickSortBenchmark : QuickSortBenchmark() {
    @Param(value = ["1", "2", "4", "8"])
    var parallelism: Int = 0

    @Benchmark
    fun parallelUsingCoroutines() {
        array.parallelQuickSortUsingCoroutines(parallelism)
    }

    @Benchmark
    fun parallelUsingForkJoinPool() {
        array.parallelQuickSortUsingForkJoinPool(parallelism)
    }
}