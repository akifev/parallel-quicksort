package me.akifev.sort

import org.openjdk.jmh.annotations.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

@Suppress("unused")
@Fork(3)
@State(Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.AverageTime)
@Warmup(iterations = 5)
@Measurement(iterations = 5)
open class QuickSortBenchmark {
    protected lateinit var array: IntArray

    @Param(value = ["${1e7.toInt()}", "${1e8.toInt()}"])
    var arraySize: Int = 0

    @Setup(Level.Iteration)
    fun init() {
        array = IntArray(arraySize) { Random.nextInt() }
    }
}
