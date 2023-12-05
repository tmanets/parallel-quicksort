import kotlin.random.Random

interface QuickSort {
    fun IntArray.sort(left: Int = 0, right: Int = size - 1)

    fun IntArray.swap(i: Int, j: Int) {
        val temp = this[i]
        this[i] = this[j]
        this[j] = temp
    }

    fun IntArray.partition(left: Int, right: Int): Int {
        val pivot = Random.nextInt(left, right + 1)
        val pivotValue = this[pivot]
        var i = left
        var j = right
        while (i <= j) {
            while (this[i] < pivotValue) {
                i++
            }
            while (this[j] > pivotValue) {
                j--
            }
            if (i >= j) {
                break
            }
            swap(i++, j--)
        }
        return j
    }
}