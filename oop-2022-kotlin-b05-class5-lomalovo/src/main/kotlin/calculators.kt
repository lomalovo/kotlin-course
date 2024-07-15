import kotlin.math.floor
import kotlin.math.max

interface Calculator{
    fun amountFor(perf: Performance):Int
    fun volumeCreditsFor(perf: Performance):Int
}

class ComedyCalculator :Calculator{
    companion object ComedyCalculatorFactory : CalculatorFactory(){
        override fun createCalculator() = ComedyCalculator()
    }
    override fun amountFor(perf: Performance): Int {
        var result = 30000
        if (perf.audience > 20) {
            result += 10000 + 500 * (perf.audience - 20)
        }
        result += 300 * perf.audience
        return result
    }

    override fun volumeCreditsFor(perf: Performance): Int {
        return max(perf.audience - 30, 0) + floor(perf.audience / 5.0).toInt()
    }

}

class TragedyCalculator :Calculator{
    companion object TragedyCalculatorFactory : CalculatorFactory(){
        override fun createCalculator() = TragedyCalculator()
    }
    override fun amountFor(perf: Performance): Int {
        var result = 40000
        if (perf.audience > 30) {
            result += 1000 * (perf.audience - 30)
        }
        return result
    }

    override fun volumeCreditsFor(perf: Performance): Int {
        return max(perf.audience - 30, 0)
    }

}
