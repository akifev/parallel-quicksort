package me.akifev.sort

import kotlinx.coroutines.*
import java.util.concurrent.*

@OptIn(ExperimentalCoroutinesApi::class)
fun IntArray.parallelQuickSortUsingCoroutines(parallelism: Int): Unit =
    runBlocking(context = Dispatchers.Default.limitedParallelism(parallelism)) {
        coroutineScope {
            parallelQuickSortUsingCoroutines(0, size)
        }
    }

private suspend fun IntArray.parallelQuickSortUsingCoroutines(l: Int, r: Int) {
    if (r - l < 1000) {
        sequentialQuickSort(l, r)
        return
    }

    val m = makePartition(l, r)
    coroutineScope {
        if (m > l) {
            launch { parallelQuickSortUsingCoroutines(l, m) }
        }
        if (r > m) {
            launch { parallelQuickSortUsingCoroutines(m, r) }
        }
    }
}