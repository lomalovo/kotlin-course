import org.junit.Assert
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.FileWriter
import java.io.PrintStream
import java.lang.StringBuilder
import kotlin.random.Random

class Test14 {
    companion object {
        val newline = System.getProperty("line.separator")
        val phrases = listOf (
            "Наши ожидания были иными!",
            "Какое жестокое разочарование!",
            "Возможно это и правильный ответ, но не в нашей Вселенной!",
            "У верблюда два горба, потому что жизнь - борьба!",
            "В наше время Kotlin был островом...",
            "Не хочу никого расстраивать, но это не решение.",
            "Стоит попробовать ещё разок..."
        )
    }

    private fun testStdout(expected: String, fn: () -> Unit) {
        val oldStdOut = System.out
        try {
            val stream = ByteArrayOutputStream()
            stream.use {
                PrintStream(stream, true, "UTF-8").use { System.setOut(it); fn(); }
            }
            val actual = stream.toByteArray().inputStream().bufferedReader().readText()
            if (expected != actual) {
                Assert.fail( phrases[Random.nextInt(0, phrases.size)])
            }
        } finally {
            System.setOut(oldStdOut)
        }
    }

    @Test
    fun testSampleFile() {
        val someLines = generateLines(Random.nextInt(1, 100), false)
        val correctLine = generateLines(1, true)
        val someOtherLines = generateLines(Random.nextInt(1, 100), false)
        testStdout(correctLine) {
            val data =
                """
# Here we have some data
1 -1 0 0 10 -7  3

# 10 0 0 -10
WTF!??
$someLines
$correctLine 
$someOtherLines
1 2 3 -1 -2 -3 Ура!

# End of story
"""
            val tempFile = createTempFile("kotlin-", ".txt")
            FileWriter(tempFile).use {
                it.write(data)
            }
            try {
                main(arrayOf(tempFile.absolutePath.toString()))
            }
            finally {
                tempFile.delete()
            }
        }
    }

    private fun generateLines(numOfLines: Int, correct: Boolean): String {
        val builder = StringBuilder()
        repeat(numOfLines) {
            val numbers = mutableListOf<Int>()
            val size = Random.nextInt(1, 7)
            var sum = 0
            repeat(size) {
                val number = Random.nextInt(-100, 100)
                sum += number
                numbers.add(number)
            }
            if (correct) {
                numbers.add(-sum)
            }
            else if (sum == 0) {
                numbers.add(Random.nextInt(1, 100))
            }
            builder.append(numbers.joinToString(" ", postfix = newline))
        }
        return builder.toString()
    }
}
