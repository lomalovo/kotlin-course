import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.io.path.createTempFile

class Task2Test {
    @Test
    fun testMain1() {
        val tmpFileName = createTempFile()
        val tmpFile = tmpFileName.toFile()
        File("../data/text-utf8.txt").copyTo(tmpFile, true)
        main(arrayOf(tmpFileName.toString(), "utf-8", "windows-1251"))
        assertTrue(File("../data/text-windows-1251.txt").readBytes().contentEquals(tmpFile.readBytes()))
    }

    @Test
    fun testMain2() {
        val tmpFileName = createTempFile()
        val tmpFile = tmpFileName.toFile()
        File("../data/text-windows-1251.txt").copyTo(tmpFile, true)
        main(arrayOf(tmpFileName.toString(), "windows-1251", "utf-8"))
        assertTrue(File("../data/text-utf8.txt").readBytes().contentEquals(tmpFile.readBytes()))
    }

}