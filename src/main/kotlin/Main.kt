import kotlin.random.Random
import kotlin.system.measureTimeMillis

const val ARRAY_SIZE = 1e8.toInt()
const val TEST_COUNT = 5

fun main() {
    val methods: List<QuickSort> = listOf(SequentialQuickSort(), ParallelQuickSort())
    val (seqTimes, parTimes) = List(TEST_COUNT) {
        println("run$it")
        val arr = generateRandomArray()
        println("created random array")
        methods.mapIndexed { index, method ->
            val arrCopy = arr.copyOf()
            val time = measureTimeMillis {
                with(method) {
                    arrCopy.sort()
                }
            }
            if (!validate(arrCopy)) {
                throw IllegalStateException("Not sorted")
            }
            println("method$index ended sort with time $time")
            time
        }.run { this[0] to this[1] }
    }.unzip()

    println("=============================")

    println("sequential times (in ms)")
    println(seqTimes.toString())
    println("Average is ${seqTimes.average()}")
    println("parallel times (in ms)")
    println(parTimes.toString())
    println("Average is ${seqTimes.average()}")

    println("=============================")
    println("ratio is ${seqTimes.average() / parTimes.average()}")
}

fun generateRandomArray(size: Int = ARRAY_SIZE) = IntArray(size) { Random.nextInt() }

fun validate(array: IntArray): Boolean {
    return array.asSequence().zipWithNext().all { it.first <= it.second }
}