package me.akifev.sort

import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveTask

fun IntArray.parallelQuickSortUsingForkJoinPool(parallelism: Int) {
    val forkJoinPool = ForkJoinPool(parallelism)
    forkJoinPool.invoke(object : RecursiveTask<Unit>() {
        override fun compute() {
            parallelQuickSortUsingForkJoinPool(0, size)
        }
    })
    forkJoinPool.shutdown()
}

private fun IntArray.parallelQuickSortUsingForkJoinPool(l: Int, r: Int) {
    if (r - l < 1000) {
        sequentialQuickSort(l, r)
        return
    }

    val m = makePartition(l, r)
    val tasks = buildList {
        if (m > l) {
            add(object : RecursiveTask<Unit>() {
                override fun compute() {
                    parallelQuickSortUsingForkJoinPool(l, m)
                }
            })
        }
        if (r > m) {
            add(object : RecursiveTask<Unit>() {
                override fun compute() {
                    parallelQuickSortUsingForkJoinPool(m, r)
                }
            })
        }
    }
    tasks.map { it.fork() }.map { it.join() }
}