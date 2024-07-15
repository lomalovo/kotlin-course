import kotlin.test.Test
import kotlin.test.assertEquals

internal class Task1Test {
    @Test
    fun testStringLength() {
        assertEquals("Длина: 5", stringLength("hello"))
        assertEquals("Длина: 0", stringLength(""))
        assertEquals("Длина: 1000", stringLength("x".repeat(1000)))
    }

    @Test
    fun testFirstLastCode() {
        assertEquals(Pair(32, 32), firstLastCodes(" "))
        assertEquals(Pair('x'.code, 'y'.code), firstLastCodes("xxxxxxxxyyyyyyyy"))
    }

    @Test
    fun testCountDigits() {
        assertEquals(0, countDigits("xxx"))
        assertEquals(3, countDigits("x1x2x3"))
        assertEquals(3, countDigits("1a2b3c"))
    }

    @Test
    fun testCountDigits2() {
        assertEquals(0, countDigits2("xxx"))
        assertEquals(3, countDigits2("x1x2x3"))
        assertEquals(3, countDigits2("1a2b3c"))
    }

    @Test
    fun testCalcDigits() {
        assertEquals(0, calcDigits("3-3"))
        assertEquals(1, calcDigits("4+7-2-8"))
        assertEquals(-1, calcDigits("2+8-4-7"))
    }

    @Test
    fun testReplaceWithString() {
        assertEquals("Миру война", replaceWithString("Миру мир", "мир", "война"))
        assertEquals("xxxcd", replaceWithString("abcd", "ab", "xxx"))
        assertEquals("xxxcdab", replaceWithString("abcdab", "ab", "xxx"))
    }
}
