import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Task3Test {
    @Test
    fun testAllDigits() {
        assertTrue(allDigits("0123"))
        assertTrue(allDigits("9876543210"))
        assertTrue(!allDigits("0123x"))
        assertTrue(!allDigits("xxx"))
    }

    @Test
    fun testContainsABC() {
        assertTrue(containsABC("01aBC"))
        assertTrue(containsABC("ABC"))
        assertTrue(containsABC("xabcx"))
        assertTrue(!containsABC("abD"))
        assertTrue(!containsABC("bcbcbca"))
    }

    @Test
    fun testFindDigitalSubstring() {
        assertEquals("000", findDigitalSubstring("000"))
        assertEquals("123", findDigitalSubstring("abc123def456"))
        assertEquals("1234", findDigitalSubstring("1234def456"))
        assertEquals("", findDigitalSubstring("abcde"))
        assertEquals("", findDigitalSubstring(""))
    }

    @Test
    fun testHideDigits() {
        assertEquals("abcxxxdef", hideDigits("abc00def", "xxx"))
        assertEquals("abcxxxdefxxxghe", hideDigits("abc00def09876543ghe", "xxx"))
        assertEquals("abcdef", hideDigits("abcdef", "xxx"))
    }
}