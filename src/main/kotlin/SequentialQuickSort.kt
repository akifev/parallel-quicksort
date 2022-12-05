import kotlin.random.Random

fun IntArray.sequentialQuickSort() {
    sequentialQuickSort(0, size)
}

internal fun IntArray.sequentialQuickSort(l: Int, r: Int) {
    if (r - l <= 1) {
        return
    }
    val m = makePartition(l, r)
    sequentialQuickSort(l, m)
    sequentialQuickSort(m, r)
}

internal fun IntArray.makePartition(l: Int, r: Int): Int {
    val xIndex = Random.nextInt(l, r)
    val x = this[xIndex]
    var p1 = l
    var p2 = r - 1
    while (p1 <= p2) {
        while (this[p1] < x) p1++
        while (this[p2] > x) p2--
        if (p1 <= p2) swap(p1++, p2--)
    }
    return p1
}