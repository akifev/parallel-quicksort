internal fun IntArray.swap(i: Int, j: Int) {
    val tmp = this[i]
    this[i] = this[j]
    this[j] = tmp
}

internal fun IntArray.checkSorted() {
    check(indices.all { i -> i == 0 || this[i] >= this[i - 1] })
}