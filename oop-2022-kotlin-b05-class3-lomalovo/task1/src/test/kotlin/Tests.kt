import kotlin.test.*
import kotlin.jvm.javaClass
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.primaryConstructor

internal class Tests {

    @Test
    fun `check Computer`() {
        val computerInterface = loadClass("Computer")
        assertTrue(
            actual = computerInterface.memberFunctions.first().let { it.name == "calculateAnswer" },
            message = "Computer must define calculateAnswer function"
        )
    }

    @Test
    fun `check Desktop`() {
        val computerInterface = loadClass("Computer")
        val desktop = loadClass("Desktop")
        assertTrue(
            actual = desktop.isSubclassOf(computerInterface),
            message = "Desktop must implement Computer"
        )
        assertEquals(
            expected = 5,
            actual = callFunOnInstance(desktop, "calculateAnswer", 5) as Int?,
            message = "Desktop's calculateAnswer must return fixed result"
        )
        assertEquals(
            expected = 10,
            actual = callFunOnInstance(desktop, "calculateAnswer", 10) as Int?,
            message = "Desktop's calculateAnswer must return fixed result"
        )
    }

    @Test
    fun `check SummingCloud`() {
        val computerInterface = loadClass("Computer")
        val summingCloud = loadClass("SummingCloud") 
        assertTrue(
            actual = summingCloud.isSubclassOf(computerInterface),
            message = "SummingCloud must implement Computer"
        )

        assertEquals(
            expected = 1,
            actual = callFunOnInstance(summingCloud, "calculateAnswer", 1) as Int?,
            message = "SummingCloud's calculateAnswer must be a sum of Desktop's results"
        )

        assertEquals(
            expected = 55,
            actual = callFunOnInstance(summingCloud, "calculateAnswer", 10) as Int?,
            message = "SummingCloud's calculateAnswer must be a sum of Desktop's results"
        )
    }

    private fun callFunOnInstance(clazz: KClass<*>, funName: String, vararg args: Any?): Any? {
        val instance = clazz.primaryConstructor?.call(*args)
        return instance?.javaClass?.getMethod(funName)?.invoke(instance)
    }

    private fun loadClass(className: String): KClass<*> {
        return Class.forName(className).kotlin
    }
}