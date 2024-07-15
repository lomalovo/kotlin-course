import java.text.NumberFormat
import java.util.*

fun renderToPlainText(statementData: StatementData): String {
    val result = StringBuilder("Statement for ${statementData.customer}\n")
    statementData.statementLines.forEach {
        result.appendLine(it)
    }
    result.append("Amount owed is ${asUSD(statementData.totalAmount)}\n")
    result.append("You earned ${statementData.volumeCredits} credits\n")
    return result.toString()
}
fun renderToHtml(statementData: StatementData):String{
    val result = StringBuilder("<!DOCTYPE html>\n" +
            "<html>\n" +
            "   <head>\n" +
            "      <meta charset=\"utf-8\" />\n" +
            "      <title>Statement for ${statementData.customer}</title>\n" +
            "   </head>\n" +
            "   <body>\n" +
            "     <h1>Statement for ${statementData.customer}</h1>\n" +
            "     <table>\n" +
            "         <thead>\n" +
            "             <td>Play</td><td>Amount</td><td>Seats</td>\n" +
            "         </thead>")
    statementData.statementLines.forEach {
        result.appendLine(it.toHTML())
    }
    result.appendLine("     </table>\n" +
            "     <p>Amount owed is ${asUSD(statementData.totalAmount)}</p>")
    result.appendLine("     <p>You earned ${statementData.volumeCredits} credits</p>\n" +
            "   </body>\n" +
            "</html>")
    return result.toString()
}

fun asUSD(thisAmount: Int): String? = NumberFormat.getCurrencyInstance(Locale.US).format(thisAmount / 100)