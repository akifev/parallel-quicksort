import kotlinx.coroutines.*

@OptIn(ExperimentalCoroutinesApi::class)
fun IntArray.parallelQuickSort(parallelism: Int) =
    runBlocking(context = Dispatchers.Default.limitedParallelism(parallelism)) {
        coroutineScope {
            parallelQuickSort(0, size)
        }
    }

private suspend fun IntArray.parallelQuickSort(l: Int, r: Int) {
    if (r - l < 1000) {
        sequentialQuickSort(l, r)
        return
    }

    val m = makePartition(l, r)
    coroutineScope {
        if (m > l) {
            launch { parallelQuickSort(l, m) }
        }
        if (r > m) {
            launch { parallelQuickSort(m, r) }
        }
    }
}