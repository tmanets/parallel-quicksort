class SequentialQuickSort : QuickSort {
    override fun IntArray.sort(left: Int, right: Int) {
        if (left < right) {
            val pivot = partition(left, right)
            sort(left, pivot)
            sort(pivot + 1, right)
        }
    }
}