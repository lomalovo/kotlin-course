class OddList<T>(private val originalList: List<T>) : List<T> {
    override val size: Int
        get() = originalList.size/2

    override fun contains(element: T): Boolean = originalList.any{it==element && originalList.indexOf(it)%2==1}

    override fun containsAll(elements: Collection<T>): Boolean = elements.all{contains(it)}

    override fun get(index: Int): T = originalList[2*index+1]

    override fun indexOf(element: T): Int = if (contains(element)) originalList.indexOf(element)/2 else -1

    override fun isEmpty(): Boolean = originalList.size<2

    override fun iterator(): Iterator<T> = listIterator()

    override fun lastIndexOf(element: T): Int = originalList.filterIndexed { index, _ -> index % 2 == 1 }.lastIndexOf(element)

    override fun listIterator(): ListIterator<T> = listIterator(0)

    override fun listIterator(index: Int): ListIterator<T> = object : ListIterator<T> {
        var currentIndex : Int = index - 1

        override fun hasNext(): Boolean = currentIndex!=size-1

        override fun hasPrevious(): Boolean = currentIndex>=0

        override fun next(): T {
            if(hasNext()) {
                currentIndex+=1
                return originalList[currentIndex*2+1]
            }
            else {
                throw NoSuchElementException()
            }
        }

        override fun previous(): T {
            if(hasPrevious()) {
                currentIndex-=1
                return originalList[2*currentIndex+3]
            }
            else throw NoSuchElementException()
        }

        override fun nextIndex(): Int = currentIndex+1

        override fun previousIndex(): Int = if(hasPrevious()) currentIndex else -1
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<T> = OddList(originalList.subList(fromIndex*2,toIndex*2+1))

    override fun equals(other: Any?): Boolean = originalList.filterIndexed { index, _ -> index % 2 == 1 } == other

    override fun hashCode(): Int = originalList.filterIndexed { index, _ -> index % 2 == 1 }.hashCode()
}

fun main() {
    val originalList = mutableListOf(0, 1, 2, 3, 4, 5)
    val oddList = OddList(originalList)
    println(oddList.joinToString()) // 1, 3, 5
    originalList[3] = -3
    println(oddList.joinToString()) // 1, -3, 5
}
