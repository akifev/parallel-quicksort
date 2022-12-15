package me.akifev.sort

class SequentialQuickSortTest : QuickSortTest() {
    override fun IntArray.sortArray() = sequentialQuickSort()
}