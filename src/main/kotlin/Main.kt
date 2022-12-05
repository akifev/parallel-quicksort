import java.util.logging.FileHandler
import java.util.logging.Formatter
import java.util.logging.LogRecord
import java.util.logging.Logger
import kotlin.random.Random

fun main(args: Array<String>) {
    val logger = getLogger()
    testSpeedup(logger = logger)
}

private fun getLogger(): Logger? {
    val handler = FileHandler("README.md")
    val logger = Logger.getLogger("Logger")
    logger.addHandler(handler)
    handler.formatter = object : Formatter() {
        override fun format(record: LogRecord): String {
            return record.message + System.lineSeparator() + System.lineSeparator()
        }
    }
    return logger
}

private fun testSpeedup(
    arraySize: Int = 10_000_000,
    parallelism: Int = 4,
    logger: Logger? = null
): Double {
    logger?.info("Speedup test (arraySize = $arraySize, parallelism = $parallelism)")

    val seqAvgTime = test(
        name = "sequential quick sort",
        arraySize = arraySize,
        logger = logger
    ) { it.sequentialQuickSort() }
    val parAvgTime = test(
        name = "parallel quick sort",
        arraySize = arraySize,
        logger = logger
    ) { it.parallelQuickSort(parallelism) }

    val speedup = seqAvgTime.toDouble() / parAvgTime

    logger?.info("Speedup: $speedup")

    return speedup
}

private fun test(
    name: String,
    arraySize: Int = 10_000_000,
    iterations: Int = 5,
    warmupIterations: Int = 2,
    logger: Logger? = null,
    sortFunc: (IntArray) -> Unit
): Long {
    repeat(warmupIterations) {
        doIteration(arraySize, sortFunc)
    }

    var total = 0L
    repeat(iterations) {
        val iterTime = doIteration(arraySize, sortFunc)
        total += iterTime
    }

    val avgTime = total / iterations

    logger?.info("Test $name: $avgTime ms")

    return avgTime
}

private fun doIteration(arraySize: Int, sortFunc: (IntArray) -> Unit): Long {
    val array = IntArray(arraySize) { Random.nextInt() }
    val start = System.currentTimeMillis()
    sortFunc(array)
    val end = System.currentTimeMillis()
    array.checkSorted()

    return end - start
}