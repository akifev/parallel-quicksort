package me.akifev.sort

class ParallelQuickSortUsingCoroutinesTest : QuickSortTest() {
    override fun IntArray.sortArray() = parallelQuickSortUsingCoroutines(parallelism)

    companion object {
        const val parallelism = 4
    }
}