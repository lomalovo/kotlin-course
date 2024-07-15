import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.reflect.full.declaredFunctions
import kotlin.reflect.full.isSubclassOf
import kotlin.test.*

internal class Task4Tests {
    private val standardOut = System.out
    private val stream = ByteArrayOutputStream()

    @BeforeTest
    fun setUp() {
        System.setOut(PrintStream(stream, true, Charsets.UTF_8))
    }

    @AfterTest
    fun tearDown() {
        System.setOut(standardOut)
    }

    @Test
    fun `check Box`() {
        val box = Class.forName("Box").kotlin
        assertEquals(
            expected = setOf("view", "get", "put"),
            actual = box.declaredFunctions.map { it.name }.toSet(),
            message = "Box declares wrong function members"
        )
    }

    @Test
    fun `check BoxList`() {
        val boxList = Class.forName("BoxList").kotlin
        val arrayList = Class.forName("java.util.ArrayList").kotlin
        assertTrue(
            actual = boxList.isSubclassOf(arrayList),
            message = "Box declares wrong function members"
        )
        assertNotNull(
            actual = boxList.declaredFunctions.find { it.name == "add" },
            message = "BoxList should define 'add'"
        )
    }
    
    @Test
    fun `check part1 output`() {
        part1()
        assertEquals("""
            === Часть 1: класс Box ===
            Box b: 6
            ERROR: Box is not empty
            ERROR: Box is empty
            ERROR: Box is empty
            Box b: 0
            Box b2: hello
            String's length is 5
            String's length is 3
        """.trimIndent(), stream.toString(Charsets.UTF_8).trim().lines().joinToString("\n"))
    }

    @Test
    fun `check part2 output` () {
        part2()
        assertEquals("""
            === Часть 2: функция convert ===
            Box stringBox: 42!
            ERROR: Box is empty
            Box intBox2: 3
            Box intBox3: 4
        """.trimIndent(), stream.toString(Charsets.UTF_8).trim().lines().joinToString("\n"))
    }

    @Test
    fun `check part3 output` () {
        part3()
        assertEquals("""
            === Часть 3: класс BoxList ===
            [[1], [2], [3], [4], [5], [6], [7], [8]]
            [[a], [b], [c], [d], [e], [f], [g], [h]]
        """.trimIndent(), stream.toString(Charsets.UTF_8).trim().lines().joinToString("\n"))
    }
}