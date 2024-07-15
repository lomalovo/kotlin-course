data class StatementLine(val playName: String, val amount: Int,
                         val volumeCredits: Int, val audience: Int) {
    override fun toString(): String {
        return " $playName: ${asUSD(amount)} (${audience} seats)"
    }
    fun toHTML(): String{
        return "         <tr>\n" +
                "             <td>$playName</td><td>${asUSD(amount)}</td><td>${audience}</td>\n" +
                "         </tr>"
    }
}

data class StatementData(val customer: String,
                         val statementLines: List<StatementLine>) {
    val volumeCredits = statementLines.sumOf { it.volumeCredits }
    val totalAmount = statementLines.sumOf { it.amount }
}

fun statement(invoice: Invoice, plays: List<Play>, type:RenderType): String {
    val statementData = statementData(invoice, plays)
    if(type==RenderType.PlainText) return renderToPlainText(statementData)
    else return renderToHtml(statementData)
}

private fun statementData(invoice: Invoice, plays: List<Play>): StatementData {
    fun findPlay(perf: Performance): Play {
        return plays.find { it == perf.play } ?: throw Exception("Unknown play: ${perf.play}")
    }

    fun statementLine(perf: Performance): StatementLine {
        val play = findPlay(perf)
        return StatementLine(
            play.name, play.calculator.amountFor(perf),
            play.calculator.volumeCreditsFor(perf), perf.audience
        )
    }

    return StatementData(
        invoice.customer,
        invoice.performances.map(::statementLine)
    )
}