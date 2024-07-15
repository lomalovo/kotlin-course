import java.io.File
import kotlin.test.*

internal class Task1Test {
    @Test
    fun testApplyOperation() {
        assertEquals(10, applyOperation('*', 5, 2))
        assertEquals(2, applyOperation('/', 10, 5))
        assertFailsWith<UnsupportedOperation> { applyOperation('+', 0, 0) }
        assertFailsWith<DivideByZero> { applyOperation('/', 1, 0) }
    }
    @Test
    fun testApplySchema() {
        assertEquals(24, applySchema("***")(listOf(1,2,3,4)))
        assertEquals(1, applySchema("**/")(listOf(1,2,3,4)))
        assertFailsWith<NotEnoughNumbers> { applySchema("**/")(listOf(1,2,3)) }
    }
    @Test
    fun testFormatLHS() {
        assertEquals("1*2*3*4", formatLHS("***",listOf("1","2","3","4")))
        assertEquals("1/2*932*4", formatLHS("/**",listOf("1","2","932","4")))
    }
    @Test
    fun testProcessString() {
        assertEquals("1*2*3*4=24", processString("***","1,2,3,4"))
        assertFailsWith<IncorrectFormatOfString> { processString("***","1,2,3,4ads") }
    }
    @Test
    fun testProcessFile() {

        assertFailsWith<MissingInputFile> { processFiles("../423.txt","../numbers.txt","../output.txt")}
        assertFailsWith<MissingInputFile> { processFiles("../schema.txt","../123.txt","../output.txt")}
        assertFailsWith<FileExists> { processFiles("../schema.txt","../numbers.txt","../numbers.txt")}
        processFiles("../schema.txt","../numbers.txt","../output.txt")
        assertEquals(File("../schema.txt").readLines().size, File("../output.txt").readLines().size)
    }
}