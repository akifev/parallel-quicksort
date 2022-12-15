package me.akifev.sort

class ParallelQuickSortUsingForkJoinPoolTest : QuickSortTest() {
    override fun IntArray.sortArray() = parallelQuickSortUsingForkJoinPool(parallelism)

    companion object {
        const val parallelism = 4
    }
}