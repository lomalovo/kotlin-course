import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.reflect.KClass
import kotlin.reflect.full.*
import kotlin.test.*

internal class Task2Tests {
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
    fun `check Talkable`() {
        val talkable = loadClass("Talkable")
        assertEquals(
            expected = setOf("sound", "talk"),
            actual = talkable.declaredMembers.map { it.name }.toSet(),
            message = "Talkable must define 'sound' and 'talk'"
        )
    }

    @Test
    fun `check AbstractAnimal`() {
        val talkable = loadClass("Talkable")
        loadClass("AbstractAnimal").apply {
            assertTrue(isAbstract, "AbstractAnimal must be abstract")
            assertTrue(
                actual = isSubclassOf(talkable),
                message = "AbstractAnimal must implement Talkable"
            )
            assertNull(
                actual = declaredMemberProperties.find { it.name == "sound" },
                message = "The `sound` property shouldn't be overridden in AbstractAnimal:"
            )
        }
    }

    @Test
    fun `check animals`() {
        val talkable = loadClass("Talkable")
        assertTrue(
            actual = loadClass("Cat").isSubclassOf(talkable),
            message = "Animals must implement Talkable"
        )
        assertTrue(
            actual = loadClass("Dog").isSubclassOf(talkable),
            message = "Animals must implement Talkable"
        )
        assertTrue(
            actual = loadClass("Goose").isSubclassOf(talkable),
            message = "Animals must implement Talkable"
        )
    }

    @Test
    fun `check RobotVacuum`() {
        val robot = loadClass("RobotVacuum")
        assertTrue(
            actual = robot.isSubclassOf(loadClass("Talkable")),
            message = "RobotVacuum should implement Talkable"
        )
        assertTrue(
            actual = !robot.isSubclassOf(loadClass("AbstractAnimal")),
            message = "RobotVacuum is not an animal"
        )
    }

    @Test
    fun `check talks` () {
        Cat().talk()
        Dog().talk()
        Goose().talk()
        callFunOnInstance(loadClass("RobotVacuum"), "talk")
        
        assertEquals("""
            мяу-мяу-мяу
            гав-гав-гав
            га-га-га
            ур-ур-ур-ур
        """.trimIndent(), stream.toString(Charsets.UTF_8).trim().lines().joinToString("\n"))
    }

    @Test
    fun `no overriding 'talk' in subclasses`() {
        assertNull(
            actual = loadClass("Cat").declaredFunctions.find { it.name == "talk" },
            message = "The `talk` function shouldn't be overridden in subclasses:"
        )
        assertNull(
            actual = loadClass("Dog").declaredFunctions.find { it.name == "talk" },
            message = "The `talk` function shouldn't be overridden in subclasses:"
        )
        assertNull(
            actual = loadClass("Goose").declaredFunctions.find { it.name == "talk" },
            message = "The `talk` function shouldn't be overridden in subclasses:"
        )
    }

    @Test
    fun `overriding 'sound' in subclasses`() {
        assertNotNull(
            actual = loadClass("Cat").memberProperties.find { it.name == "sound" },
            message = "The `sound` property should be overridden in subclasses"
        )
        assertNotNull(
            actual = loadClass("Dog").memberProperties.find { it.name == "sound" },
            message = "The `sound` property should be overridden in subclasses"
        )
        assertNotNull(
            actual = loadClass("Goose").memberProperties.find { it.name == "sound" },
            message = "The `sound` property should be overridden in subclasses"
        )
    }
    
    private fun loadClass(className: String): KClass<*> {
        return Class.forName(className).kotlin
    }

    private fun callFunOnInstance(clazz: KClass<*>, funName: String, vararg args: Any?): Any? {
        val instance = clazz.primaryConstructor?.call(*args)
        return instance?.javaClass?.getMethod(funName)?.invoke(instance)
    }
}