import java.io.ByteArrayOutputStream
import java.io.File
import kotlin.test.*
import kotlin.test.assertEquals
import java.io.PrintStream
internal class Test1 {
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
    fun testGetEverySecond() {
        assertEquals(listOf("123","345"),getEverySecond("hsdf${separator}123${separator}uuuuu${separator}345"))
    }
    @Test
    fun testPut() {
        File("testPut.txt").createNewFile()
        main(arrayOf("put","testPut.txt","123","456"))
        val string = "123".hashCode().toString() + "${separator}123${separator}456"
        assertEquals(File("testPut.txt").readLines().first(), string)
        main(arrayOf("put","testPut.txt","123","456"))
        assertEquals(File("testPut.txt").readLines().first(), string)

    }
    @Test
    fun testGet() {
        File("testGet.txt").createNewFile()
        main(arrayOf("put","testGet.txt","123","456"))
        main(arrayOf("get","testGet.txt","123"))
        assertEquals("456", stream.toString().trim())
    }
    @Test
    fun testRemove() {
        File("testRemove.txt").createNewFile()
        main(arrayOf("put","testRemove.txt","123","456"))
        main(arrayOf("remove","testRemove.txt","123"))
        assertEquals("", stream.toString().trim())
    }
}
