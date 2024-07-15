import kotlin.reflect.full.*
import kotlin.test.*

internal class Tests {

    @Test
    fun `check Creature interface`() {
        assertNotNull(
            actual = Class.forName("Creature").kotlin
                .declaredMemberProperties.find { it.name == "message" },
            message = "There should be Creature interace with the message property"
        )
    }
    
    @Test
    fun `check adapters`() {
        checkAdapter(Human(), Human().greeting, "HumanAdapter")
        checkAdapter(Dog(), Dog().bark, "DogAdapter")
        checkAdapter(Alien(), Alien().command, "AlienAdapter")
    }

    @Test
    fun `check RobotVacuumAdapter`() {
        val robotVacuumClass = Class.forName("RobotVacuum").kotlin
        val robotVacuum = robotVacuumClass.primaryConstructor?.call()
        checkAdapter(robotVacuum, "ур", "RobotVacuumAdapter")
    }
    
    private fun checkAdapter(origObj: Any?, origMessage: String, adapterClassName: String) {
        val creature = Class.forName("Creature").kotlin
        Class.forName(adapterClassName).kotlin.apply {
            assertTrue(
                actual = isSubclassOf(creature),
                message = "Adapters must implement Creature"
            )
            val adapter = primaryConstructor?.call(origObj)
            val prop = declaredMemberProperties.find { it.name == "message" }
            assertEquals(
                expected = origMessage,
                actual = prop?.getter?.call(adapter),
                message = "Adapter's message should be same as one of the original object"
            )
        }
    }
}
