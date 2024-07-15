import java.io.ByteArrayOutputStream
import java.io.PrintStream
import java.util.*
import kotlin.math.E
import kotlin.math.exp
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.BeforeTest
import kotlin.test.AfterTest

internal class Task4Test {
    private val standardOut = System.out
    private val stream = ByteArrayOutputStream()

    @BeforeTest
    fun setUp() {
        System.setOut(PrintStream(stream))
    }

    @AfterTest
    fun tearDown() {
        System.setOut(standardOut)
    }

    @Test
    fun testPrintFrame1() {
        printFrame(5,3,'+')
        assertEquals("""
            +++++
            +   +
            +++++
        """.trimIndent(), stream.toString().trim().lines().joinToString("\n"))
    }

    @Test
    fun testPrintFrame2() {
        printFrame2(5,3,'+')
        assertEquals("""
            +++++
            +   +
            +++++
        """.trimIndent(), stream.toString().trim().lines().joinToString("\n"))
    }

    @Test
    fun testGcd() {
        assertEquals(1, gcd(2, 3))
        assertEquals(10, gcd(10, 20))
        assertEquals(5, gcd(15, 20))
        assertEquals(15, gcd(413100, 283935))
    }

    @Test
    fun testExpTaylor() {
        assertEquals(1.0, expTaylor(0.0, 10), 1e-2)
        assertEquals(E, expTaylor(1.0, 10), 1e-2)
        val x = Random().nextDouble()
        assertEquals(exp(x), expTaylor(x, 1000), 1e-10)
    }
}
