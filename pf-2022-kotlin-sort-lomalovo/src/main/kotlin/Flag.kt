enum class Flag (
    var shortArg:String,
    var longArg:String,
    var comp: (Comparator<String>) -> Comparator<String>)
{
    REVERSE("-r","--reverse",{comparator -> Comparator {o1, o2 -> -comparator.compare(o1, o2)}}),
    NUMBER("-n","--numeric-sort",{
        Comparator{ o1, o2 ->
        val i1 = o1.toIntOrNull()
        val i2 = o2.toIntOrNull()
            when{
                i1!=null && i2!=null -> i1-i2
                i1 == null -> 1
                i2 == null -> -1
                else -> 0
            }
        }
    }),
    IGNORE_CASE("-f","--ignore-case",{comparator -> Comparator{o1,o2 ->
        val i1 = o1.lowercase()
        val i2 = o2.lowercase()
        comparator.compare(i1,i2)
    }}),
    VERSION("-v","--version-sort",{
        Comparator { o1, o2 ->
            val regex = """((\d+)\.)+(\d+)"""
            if (o1.matches(Regex(regex)) && o2.matches(Regex(regex))) {
                val firstVersion = o1.split(".").map{it.toInt()}
                val secondVersion = o2.split(".").map{it.toInt()}
                for (i in 0 until kotlin.math.min(firstVersion.size, secondVersion.size)) {
                    if (firstVersion[i] > secondVersion[i]) {
                        return@Comparator 1
                    }
                    if (firstVersion[i] < secondVersion[i]) {
                        return@Comparator -1
                    }
                }
                return@Comparator firstVersion.size-secondVersion.size
            }
            when {
                o1.matches(Regex(regex)) -> -1
                o2.matches(Regex(regex)) -> 1
                else -> 0
            }
        }
    })
}