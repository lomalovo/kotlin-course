import java.io.File
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class Task1Test {
    @Test
    fun testIPv4Addr() {
        assertEquals(2130706433U, IPv4Addr("127.0.0.1").intValue)
        assertEquals(1U, IPv4Addr("0.0.0.1").intValue)
        assertEquals(16843009U, IPv4Addr("1.1.1.1").intValue)
        assertEquals(4294967295U, IPv4Addr("255.255.255.255").intValue)
    }

    @Test
    fun testCompareIPv4Addr() {
        assertTrue(IPv4Addr("127.0.0.1") < IPv4Addr("127.0.0.2"))
        assertTrue(IPv4Addr("127.0.0.0") > IPv4Addr("126.255.255.255"))
    }

    @Test
    fun testParseArgs() {
        val expected = IPLookupArgs("../data/query.ips", listOf("../data/1.iprs", "../data/2.iprs"))
        assertEquals(expected, parseArgs(arrayOf("../data/query.ips", "../data/1.iprs", "../data/2.iprs")))
        assertEquals(null, parseArgs(arrayOf("../data/query.ips")))
        assertEquals(null, parseArgs(arrayOf()))
    }

    @Test
    fun testLoadQuery() {
        val q = loadQuery("../data/query.ips")
        assertEquals(5, q.size)
    }

    @Test
    fun testLoadRanges() {
        val ranges = loadRanges(listOf("../data/1.iprs", "../data/2.iprs"))
        assertEquals(File("../data/1.iprs").readLines().size+File("../data/2.iprs").readLines().size, ranges.size)
    }

    @Test
    fun testLoadRangesEmpty() {
        val ranges = loadRanges(listOf())
        assertTrue(findRange(ranges, IPv4Addr("60.161.226.166")) == null)
    }

    @Test
    fun testFindRange() {
        val ranges = loadRanges(listOf("../data/1.iprs"))
        assertTrue(findRange(ranges, IPv4Addr("60.161.226.166")) != null)
        assertTrue(findRange(ranges, IPv4Addr("127.0.0.1")) == null)
    }
}