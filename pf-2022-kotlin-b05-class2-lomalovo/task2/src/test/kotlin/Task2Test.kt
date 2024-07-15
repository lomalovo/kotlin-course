import kotlin.test.Test
import kotlin.test.assertEquals

class Task2Test {
    @Test
    fun testFillWithAsterisks() {
        assertEquals("a***b", fillWithAsterisks("ab", 3))
        assertEquals("a**b**c**d", fillWithAsterisks("abcd", 2))
        assertEquals("*", fillWithAsterisks("*", 5))
    }

    @Test
    fun testTabulateSquares() {
        assertEquals("""
            1 1
            2 4
            3 9
        """.trimIndent(), tabulateSquares(3))
        assertEquals("""
            1  1
            2  4
            3  9
            4 16
        """.trimIndent(), tabulateSquares(4))
        assertEquals("""
            1    1
            2    4
            3    9
            4   16
            5   25
            6   36
            7   49
            8   64
            9   81
            10 100
        """.trimIndent(), tabulateSquares(10))
    }
}