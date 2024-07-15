class Human {
    val greeting = "Привет, я человек!"
}

class Dog {
    val bark = "Гав!"
}

class Alien {
    val command = "Ты меня не видишь"
}

interface Creature{
    val message:String
}

class HumanAdapter(private val human: Human):Creature{
    override val message: String
        get() = human.greeting
}
class DogAdapter(private val dog: Dog):Creature{
    override val message: String
        get() = dog.bark
}
class AlienAdapter(private val alien: Alien):Creature{
    override val message: String
        get() = alien.command
}
class RobotVacuumAdapter(private val robotVacuum: RobotVacuum):Creature{
    override val message: String
        get() = robotVacuum.sound
}

fun main() {
    val creatures = listOf(HumanAdapter(Human()),AlienAdapter(Alien()),HumanAdapter(Human()),DogAdapter(Dog()),RobotVacuumAdapter(RobotVacuum()))
    
    println("Все сообщения:")
    creatures.forEach { println(it.message) }
}