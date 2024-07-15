import kotlin.test.Test
import kotlin.io.path.createTempFile
import kotlin.test.assertEquals

class Task2Test {
    @Test
    fun testGcd() {
        val tmpFile = createTempFile().toFile().bufferedWriter()
        assertEquals(gcd("4","6",tmpFile),2)
        assertEquals(gcd("7","49",tmpFile),7)
        assertEquals(gcd("1","6",tmpFile),1)
    }
    @Test
    fun testGenerateFile() {
        val tmpFile = createTempFile().toFile()
        generateFile(tmpFile,100)
        assertEquals(tmpFile.readLines().size, 100)
    }
    @Test
    fun testCreateLog() {
        val tmpFile = createTempFile().toFile()
        createLogFile(tmpFile, 100)
        assertEquals(tmpFile.readLines().size, 100)
    }

}