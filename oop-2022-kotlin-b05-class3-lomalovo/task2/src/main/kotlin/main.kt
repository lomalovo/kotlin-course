interface Talkable{
    val sound:String
    fun talk()
}

abstract class AbstractAnimal:Talkable {
    override fun talk(){
        println("$sound-$sound-$sound")
    }
}
class RobotVacuum:Talkable{
    override val sound: String
        get() = "ур"

    override fun talk() {
        println("$sound-$sound-$sound-$sound")
    }
}

class Cat : AbstractAnimal() {
    override val sound: String
        get() = "мяу"
}

class Dog : AbstractAnimal() {
    override val sound: String
        get() = "гав"
}

class Goose : AbstractAnimal() {
    override val sound: String
        get() = "га"
}

fun main() {
    val animals = listOf(Cat(), Dog(), Goose()) // что произойдёт, если добавить в список RobotVacuum?
    animals.forEach { it.talk() }
}