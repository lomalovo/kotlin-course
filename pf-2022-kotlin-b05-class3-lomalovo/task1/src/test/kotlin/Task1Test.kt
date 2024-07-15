import kotlin.test.Test
import kotlin.test.assertEquals

internal class Task1Test {
    @Test
    fun testRoundWinner() {
        assertEquals(null, roundWinner(Card(Rank.ACE,Suit.SPADES),Card(Rank.ACE,Suit.DIAMONDS)))
        assertEquals(Player.FIRST, roundWinner(Card(Rank.ACE,Suit.HEARTS),Card(Rank.SIX,Suit.DIAMONDS)))
        assertEquals(Player.SECOND, roundWinner(Card(Rank.JACK,Suit.CLUBS),Card(Rank.QUEEN,Suit.CLUBS)))


    }

    @Test
    fun testFullDeck() {
        val deck = fullDeck()
        assertEquals(DECK_SIZE, deck.size)
        assert(Card(Rank.JACK,Suit.CLUBS) in deck)
        assert(Card(Rank.TEN,Suit.SPADES) in deck)
        assert(Card(Rank.ACE,Suit.HEARTS) in deck)
        assert(Card(Rank.SEVEN,Suit.DIAMONDS) in deck)
    }

    @Test
    fun testRound() {
        val hand1 = mutableListOf(Card(Rank.ACE,Suit.SPADES),Card(Rank.SIX,Suit.DIAMONDS))
        val hand2 = mutableListOf(Card(Rank.ACE,Suit.HEARTS),Card(Rank.ACE,Suit.HEARTS))
        val answer = Pair(Player.SECOND, mutableListOf(Card(Rank.ACE,Suit.SPADES),Card(Rank.ACE,Suit.HEARTS),Card(Rank.SIX,Suit.DIAMONDS),Card(Rank.ACE,Suit.HEARTS)))
        assertEquals(answer,round(mapOf(Player.FIRST to hand1, Player.SECOND to hand2)))
    }

    @Test
    fun testGame2Cards() {
        val six: Card = Card(Rank.SIX,Suit.SPADES)
        val ace: Card = Card(Rank.ACE,Suit.HEARTS)
        val hand1 = Player.FIRST to mutableListOf(six)
        val hand2 = Player.SECOND to mutableListOf(ace)
        val gameWinner = game(mapOf(hand1, hand2))
        assertEquals(hand2.first, gameWinner)
    }
}