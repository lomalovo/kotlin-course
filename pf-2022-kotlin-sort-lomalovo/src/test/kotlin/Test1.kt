import java.io.ByteArrayOutputStream
import java.io.File
import java.io.PrintStream
import kotlin.test.*

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
    fun testInFlags() {
        assertTrue(inFlags("-n","--numeric-sort", arrayOf("-n","-r","-s")))
        assertTrue(inFlags("-n","--numeric-sort", arrayOf("-n","--numeric-sort","-s")))
        assertTrue(inFlags("-n","--numeric-sort", arrayOf("--numeric-sort","-r","-s")))
        assertFalse(inFlags("-n","--numeric-sort", arrayOf("-k","-r","-s")))
    }
    @Test
    fun testMergeFilesToFile(){
        mergeFilesToFile(arrayOf("111","222","123"))
        assertEquals(10+3+7, File("mergedFiles.txt").readLines().size)
    }
    @Test
    fun testMainO(){
        main(arrayOf("111","222","123"))
        main(arrayOf("111","222","123","-o"))
        assertEquals(File("outputFile.txt").readLines(), stream.toString().trim().split("\n"))
    }
    @Test
    fun testMainR(){
        main(arrayOf("111","222","123","-o"))
        main(arrayOf("111","222","123","-r"))
        assertEquals(File("outputFile.txt").readLines().reversed(), stream.toString().trim().split("\n"))
    }
    @Test
    fun testMainN(){
        main(arrayOf("111","222","123","-n","-o"))
        assertEquals(File("outputFile.txt").readLines().take(10).joinToString(" "), arrayOf("0","0","0","0","6","1221","2324","2332","2415","99999").joinToString(" "))
    }
    @Test
    fun testMainV(){
        main(arrayOf("111","222","123","-v","-o"))
        assertEquals(File("outputFile.txt").readLines().take(6).joinToString(" "), arrayOf("0.0.0","1.1.1","1.1.1.0","1.2.2","2.7.0","11.22.21").joinToString(" "))
    }
    @Test
    fun testMainI(){
        main(arrayOf("111","222","123","-f","-o"))
        assertEquals(File("outputFile.txt").readLines().takeLast(4).joinToString(" "), arrayOf("asd","Dasd","kila","qfwffds").joinToString(" "))
    }
    @Test
    fun testMainIR(){
        main(arrayOf("111","222","123","-f","-o","-r"))
        assertEquals(File("outputFile.txt").readLines().take(4).joinToString(" "), arrayOf("asd","Dasd","kila","qfwffds").reversed().joinToString(" "))
    }
}
