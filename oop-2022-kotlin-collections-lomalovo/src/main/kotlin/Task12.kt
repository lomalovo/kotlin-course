fun doSomethingStrangeWithCollection(collection: Collection<String>): Collection<String>? {
    val groupsByLength: MutableMap<Int, MutableList<String>> = collection.groupBy { it.length } as MutableMap<Int, MutableList<String>>
    var maximumSizeOfGroup = groupsByLength.maxOf { it.value.size }
    return groupsByLength.filter { it.value.size==maximumSizeOfGroup }.values.firstOrNull()
}
