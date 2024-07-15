import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Task2Test {
    @Test
    fun testAvailableFunctions() {
        assertTrue(availableFunctions.size >= 10)
    }

    @Test
    fun testTabulate() {
        val funNames = listOf("square", "square")
        val nOfPoints = 10
        val res = tabulate(InputData(0.0, 10.0, nOfPoints, funNames))
        val lines = res.toString().lines()
        assertEquals(nOfPoints + 1, lines.size)
        lines.forEach {
            assertEquals(funNames.size + 1, it.split("\\s+".toRegex()).size)
        }
    }
}