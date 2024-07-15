import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.BeforeTest
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

internal class Task1Test {
    private val standardOut = System.out
    private val standardIn = System.`in`
    private val stream = ByteArrayOutputStream()

    @BeforeTest
    fun setUp() {
        System.setOut(PrintStream(stream))
    }

    @AfterTest
    fun tearDown() {
        System.setOut(standardOut)
        System.setIn(standardIn)
    }

    @Test
    fun testSayHello() {
        sayHello("Vitaly")
        assertEquals("Hello Vitaly!", stream.toString().trim())
    }

    @Test
    fun testSayHelloNL() {
        sayHello("Vitaly\nBragilevsky")
        assertEquals("Hello Vitaly\nBragilevsky!", stream.toString().trim())
    }

    @Test
    fun testHelloUser() {
        System.setIn(ByteArrayInputStream("Kotlin".toByteArray()))
        helloUser()
        assertEquals("Hello Kotlin!", stream.toString().trim().lines().last())
    }

    @Test
    fun testSquare() {
        runOnInputCheckLastLine("5", "25") { square() }
    }

    @Test
    fun testSum() {
        runOnInputCheckLastLine("2\n3", "5") { sum () }
        runOnInputCheckLastLine("100\n-100", "0") { sum () }
        runOnInputCheckLastLine("50\n50", "100") { sum () }
    }

    private fun runOnInputCheckLastLine(input: String, expected: String, action: () -> Unit) {
        System.setIn(ByteArrayInputStream(input.toByteArray()))
        action()
        assertEquals(expected, stream.toString().trim().lines().last())
    }
}