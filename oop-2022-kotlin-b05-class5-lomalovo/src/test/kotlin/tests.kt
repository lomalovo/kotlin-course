import kotlin.test.Test
import kotlin.test.assertEquals

class TestStatement {

    @Test
    fun `statement on empty invoice`() {
        val testInvoice = Invoice("testCo", listOf())
        val plays: List<Play> = listOf()
        val stmt = """
            Statement for testCo
            Amount owed is $0.00
            You earned 0 credits
            """.trimIndent()
        assertEquals(stmt, statement(testInvoice, plays, RenderType.PlainText).trimIndent())
    }

    @Test
    fun `statement on prod data`() {
        val plays = listOf(
            Play("hamlet", "Hamlet", TragedyCalculator()),
            Play("as-like", "As You Like it", ComedyCalculator()),
            Play("othello", "Othello", TragedyCalculator())
        )
        val invoice = Invoice("BigCo", listOf(
                Performance(plays.find { it.id=="hamlet" }!!, 55),
                Performance(plays.find { it.id=="as-like" }!!, 35),
                Performance(plays.find { it.id=="othello" }!!, 40)
        ))
        val stmt = """
            Statement for BigCo
             Hamlet: $650.00 (55 seats)
             As You Like it: $580.00 (35 seats)
             Othello: $500.00 (40 seats)
            Amount owed is $1,730.00
            You earned 47 credits
            """.trimIndent()
        assertEquals(stmt, statement(invoice, plays, RenderType.PlainText).trimIndent())
    }
    @Test
    fun calculatorTest() {
        val comedy:Calculator = ComedyCalculator()
        val tragedy:Calculator = TragedyCalculator()
        assertEquals(comedy.amountFor(Performance(PLAYS.find { it.id=="as-like" }!!, 35)), 58000)
        assertEquals(comedy.volumeCreditsFor(Performance(PLAYS.find { it.id=="as-like" }!!, 35)), 12)
        assertEquals(tragedy.amountFor(Performance(PLAYS.find { it.id=="hamlet" }!!, 55)), 65000)
        assertEquals(tragedy.volumeCreditsFor(Performance(PLAYS.find { it.id=="hamlet" }!!, 55)), 25)
    }
    @Test
    fun `statement on prod data 2`() {
        val plays = listOf(
            Play("hamlet", "Hamlet", TragedyCalculator()),
            Play("as-like", "As You Like it", ComedyCalculator()),
            Play("othello", "Othello", TragedyCalculator())
        )
        val invoice = Invoice("BigCo", listOf(
            Performance(plays.find { it.id=="hamlet" }!!, 55),
            Performance(plays.find { it.id=="as-like" }!!, 35),
            Performance(plays.find { it.id=="othello" }!!, 40)
        ))
        val stmt = """
<!DOCTYPE html>
<html>
   <head>
      <meta charset="utf-8" />
      <title>Statement for BigCo</title>
   </head>
   <body>
     <h1>Statement for BigCo</h1>
     <table>
         <thead>
             <td>Play</td><td>Amount</td><td>Seats</td>
         </thead>         <tr>
             <td>Hamlet</td><td>${'$'}650.00</td><td>55</td>
         </tr>
         <tr>
             <td>As You Like it</td><td>${'$'}580.00</td><td>35</td>
         </tr>
         <tr>
             <td>Othello</td><td>${'$'}500.00</td><td>40</td>
         </tr>
     </table>
     <p>Amount owed is ${'$'}1,730.00</p>
     <p>You earned 47 credits</p>
   </body>
</html>
            """.trimIndent()
        assertEquals(stmt, statement(invoice, plays, RenderType.HtmlForm).trimIndent())
    }
}