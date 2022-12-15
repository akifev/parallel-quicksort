package me.akifev.sort

import org.openjdk.jmh.annotations.Benchmark

@Suppress("unused")
open class SequentialQuickSortBenchmark : QuickSortBenchmark() {
    @Benchmark
    fun sequential() {
        array.sequentialQuickSort()
    }
}