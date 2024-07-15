import java.io.File

data class Play(val id: String, val name: String, val calculator: Calculator)
data class Performance(val play: Play , val audience: Int)
data class Invoice(val customer: String, val performances: List<Performance>)

enum class RenderType{
    PlainText,
    HtmlForm
}

val PLAYS = readPlaysCSV()
val INVOICE = readInvoiceCSV()
abstract class CalculatorFactory{
    abstract fun createCalculator():Calculator
}
fun readPlaysCSV():List<Play>{
    val reader = File("data/plays.csv").bufferedReader()
    val tragedyFactory :CalculatorFactory = TragedyCalculator.TragedyCalculatorFactory
    val comedyFactory :CalculatorFactory = ComedyCalculator.ComedyCalculatorFactory
    fun createPlay(string:String):Play{
        val separated = string.split(",")
        return if (separated.last() == "comedy") Play(separated.first(),separated.drop(1).first(),comedyFactory.createCalculator())
        else Play(separated.first(),separated.drop(1).first(),tragedyFactory.createCalculator())
    }
    return reader.readLines().map { createPlay(it) }

}
fun readInvoiceCSV():Invoice{
    val reader = File("data/invoice.csv").bufferedReader()
    val customer = reader.readLine()
    fun createPerformance(string:String):Performance {
        val separated = string.split(",")
        return Performance(PLAYS.find { it.id==separated.first() } ?: throw Exception("Unknown play : ${separated.first()}"), separated.last().toInt())
    }
    return Invoice(customer, reader.readLines().map{createPerformance(it)})

}