import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveAction

class ParallelQuickSort : QuickSort {
    companion object {
        private const val BLOCK_SIZE = 1000
        private const val THREADS = 4
    }

    override fun IntArray.sort(left: Int, right: Int) {
        class SortAction(
            private val l: Int,
            private val r: Int
        ) : RecursiveAction() {
            override fun compute() {
                if (r - l <= BLOCK_SIZE) {
                    with(SequentialQuickSort()) {
                        this@sort.sort(l, r)
                    }
                    return
                }
                val pivot = this@sort.partition(l, r)
                invokeAll(
                    SortAction(l, pivot),
                    SortAction(pivot + 1, r)
                )
            }

        }
//        ForkJoinPool.commonPool().invoke(
//            SortAction(left, right)
//        )
        ForkJoinPool(THREADS).invoke(
            SortAction(left, right)
        )

    }
}