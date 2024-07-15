import java.io.File
import java.util.*
/*
 * Объекты этого класса создаются из строки, но хранят внутри помимо строки
 * ещё и целочисленное значение соответствующего адреса. Например, для адреса
 * 127.0.0.1 должно храниться число 1 + 0 * 2^8 + 0 * 2^16 + 127 * 2^24 = 2130706433.
 */
data class IPv4Addr (val str: String) : Comparable<IPv4Addr> {
    internal val intValue = ipstr2int()

    private fun ipstr2int(): UInt {
        val numbers = str.split(".").map{it.toInt()}
        var summ = 0
        var stepen = 1
        for(numberIndex in numbers.size-1 downTo 0){
            summ+=numbers[numberIndex]*stepen
            stepen*=256
        }
        return summ.toUInt()
    }

    // Благодаря этому методу мы можем сравнивать два значения IPv4Addr
    override fun compareTo(other: IPv4Addr): Int {
        return intValue.compareTo(other.intValue)
    }

    override fun toString(): String {
        return str
    }
}

data class IPRange(val ipFrom: IPv4Addr, val ipTo: IPv4Addr) {
    override fun toString(): String {
        return "$ipFrom,$ipTo"
    }
}

data class IPLookupArgs(val ipsFile: String, val iprsFiles: List<String>)

// Необходимо заменить на более подходящий тип (коллекцию), позволяющий
// эффективно искать диапазон по заданному IP-адресу
typealias IPRangesDatabase = TreeMap<IPv4Addr, IPv4Addr>

fun parseArgs(args: Array<String>): IPLookupArgs? {
    return if(args.size < 2) null
    else return IPLookupArgs(args.first(),args.drop(1))
}
fun loadQuery(filename: String): List<IPv4Addr> {
    return File(filename).readLines().map{IPv4Addr(it)}
}

fun loadRanges(filenames: List<String>): IPRangesDatabase {
    val database: IPRangesDatabase = TreeMap()
    for (file in filenames){
        for (range in File(file).readLines()){
            database[IPv4Addr(range.split(",").first())] = IPv4Addr(range.split(",").last())
        }
    }
    return database
}

fun findRange(ranges: IPRangesDatabase, query: IPv4Addr): IPRange? {
    val subMap = ranges.subMap(IPv4Addr("0.0.0.0"),true,query,true)
    for (element in subMap){
        if (query<=element.value){
            return IPRange(element.key,element.value)
        }
    }
    return null
}

fun main(args: Array<String>) {
    val ipLookupArgs = parseArgs(args) ?: return
    val queries = loadQuery(ipLookupArgs.ipsFile)
    val ranges = loadRanges(ipLookupArgs.iprsFiles)
    val writer = File(ipLookupArgs.ipsFile).bufferedWriter()
    queries.forEach { ip ->
        writer.write("$ip: ")
        val range = findRange(ranges, ip)
        if (range!=null) writer.write("YES ($range)")
        else writer.write("NO")
        writer.newLine()
    }
    writer.close()
}



