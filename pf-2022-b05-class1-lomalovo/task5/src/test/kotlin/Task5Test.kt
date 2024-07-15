import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.BeforeTest
import kotlin.test.AfterTest

internal class Task5Test {
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
    fun test1() {
        computeFib(arrayOf("1"))
        assertEquals("1", stream.toString().trim())
    }

    @Test
    fun test2() {
        System.setIn(ByteArrayInputStream("10".toByteArray()))
        computeFib(arrayOf())
        assertEquals("55", stream.toString().trim().lines().last())
    }

    @Test
    fun test3() {
        computeFib(arrayOf("150"))
        assertEquals("9969216677189303386214405760200", stream.toString().trim())
    }

    @Test
    fun test4() {
        System.setIn(ByteArrayInputStream("42".toByteArray()))
        computeFib(arrayOf())
        assertEquals("267914296", stream.toString().trim().lines().last())
    }
}