interface Computer{
    fun calculateAnswer() :Int
}

class Desktop(private val integer: Int) :Computer{
    override fun calculateAnswer(): Int = integer
}

class SummingCloud(n:Int):Computer{
    var list :List<Computer> = listOf()
    init{
        for(i in 1..n){
            list=list.plus(Desktop(i))
        }
    }
    override fun calculateAnswer(): Int = list.sumOf { it.calculateAnswer() }
}

fun main() {
    val answer = SummingCloud(10).calculateAnswer()
    println("Sum = $answer") // should print 55
}