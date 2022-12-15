# Parallel Quick Sort

[![Parallel Quick Sort](https://github.com/akifev/parallel-quicksort/actions/workflows/build.yml/badge.svg?branch=master)](https://github.com/akifev/parallel-quicksort/actions/workflows/build.yml)

**Parallel Quick Sort** algorithm implementation using [**Kotlin Coroutines**](https://kotlinlang.org/docs/coroutines-overview.html) and [**Java ForkJoinPool**](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ForkJoinPool.html)

### JMH Benchmark Results
```
Benchmark                                             (arraySize)  (parallelism)  Mode  Cnt      Score     Error  Units
ParallelQuickSortBenchmark.parallelUsingCoroutines      100000000              4  avgt   15   2646.403 ± 116.297  ms/op
ParallelQuickSortBenchmark.parallelUsingForkJoinPool    100000000              4  avgt   15   2529.098 ± 254.826  ms/op
SequentialQuickSortBenchmark.sequential                 100000000            N/A  avgt   15  11177.246 ± 246.883  ms/op
```
Both implementations speed up the sequential algorithm by `4.3` times on an array of size `10^8` when using `4` native threads.
> For more details, see [jmh-results.txt](src/jmh/resources/jmh-results.txt)