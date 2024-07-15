import kotlin.math.min

/*
 * Реализуйте игру "Пьяница" (в простейшем варианте, на колоде в 36 карт)
 * https://ru.wikipedia.org/wiki/%D0%9F%D1%8C%D1%8F%D0%BD%D0%B8%D1%86%D0%B0_(%D0%BA%D0%B0%D1%80%D1%82%D0%BE%D1%87%D0%BD%D0%B0%D1%8F_%D0%B8%D0%B3%D1%80%D0%B0)
 * Рука — это набор карт игрока. Карты выкладываются на стол из начала "рук" и сравниваются
 * только по значениям (масть игнорируется). При равных значениях сравниваются следующие карты.
 * Набор карт со стола перекладывается в конец руки победителя. Шестерка туза не бьёт.
 *
 * Реализация должна сопровождаться тестами.
 */

// Размер колоды
const val DECK_SIZE = 36

// Масть
enum class Suit{
    DIAMONDS,
    SPADES,
    HEARTS,
    CLUBS
}

// Значение
enum class Rank{
    SIX,
    SEVEN,
    EIGHT,
    NINE,
    TEN,
    JACK,
    QUEEN,
    KING,
    ACE
}

// Карта
data class Card(var rank:Rank, var suit:Suit)

// Тип для обозначения игрока (первый, второй)
enum class Player{
    FIRST,
    SECOND
}

// Возвращается null, если значения карт совпадают
fun roundWinner(card1: Card, card2 : Card): Player? {
    if (card1.rank.ordinal == card2.rank.ordinal) return null
    return if (card1.rank.ordinal > card2.rank.ordinal) Player.FIRST
    else Player.SECOND
}

// Колода
typealias Deck = List<Card>

// Набор карт у игрока
typealias Hand = MutableList<Card>

// Набор карт, выложенных на стол
typealias Table = MutableList<Card>

// Наборы карт у игроков
typealias Hands = Map<Player, Hand>

// Возвращает полную колоду (36 карт) в фиксированном порядке
fun fullDeck() : Deck {
    val fullDeck: MutableList<Card> = mutableListOf()
    for (rank in Rank.values()){
        for (suit in Suit.values()){
            fullDeck.add(Card(rank,suit))
        }
    }
    return fullDeck
}

// Раздача карт: случайное перемешивание (shuffle) и деление колоды пополам
fun deal(deck: Deck) : Hands {
    val shuffledDeck = deck.shuffled()
    val hands: MutableMap<Player, Hand> = mutableMapOf()
    hands[Player.FIRST] = shuffledDeck.take((shuffledDeck.size)/2).toMutableList()
    hands[Player.SECOND] = shuffledDeck.takeLast((shuffledDeck.size)/2).toMutableList()
    return hands
}

// Один раунд игры (в том числе спор при равных картах).
// Возвращается победитель раунда и набор карт, выложенных на стол.
fun round(hands: Hands): Pair<Player?, Table> {
    val table:MutableList<Card> = mutableListOf()
    for (i in 0 until min(hands[Player.FIRST]!!.size,hands[Player.SECOND]!!.size)){
        if(roundWinner(hands[Player.FIRST]!![i],hands[Player.SECOND]!![i])!=null){
            table.add(hands[Player.FIRST]!![i])
            table.add(hands[Player.SECOND]!![i])
            return Pair(roundWinner(hands[Player.FIRST]!![i],hands[Player.SECOND]!![i]),table)
        }
        else{
            table.add(hands[Player.FIRST]!![i])
            table.add(hands[Player.SECOND]!![i])
        }
    }
    if(hands[Player.FIRST]!!.size>=hands[Player.SECOND]!!.size) return Pair(Player.FIRST,table)
    return Pair(Player.SECOND,table)
}

// Полный цикл игры (возвращается победивший игрок)
// в процессе игры печатаются ходы
fun game(hands: Hands): Player {
    val hand1:MutableList<Card>?= hands[Player.FIRST]
    val hand2:MutableList<Card>?= hands[Player.SECOND]
    print("$hand1\n$hand2")
    while (hand1!!.isNotEmpty() and hand2!!.isNotEmpty()){
        val table = round(mapOf(Player.FIRST to hand1,Player.SECOND to hand2)).second
        val winner = round(mapOf(Player.FIRST to hand1,Player.SECOND to hand2)).first
        for (card in table){
            if((card in hand1) and (winner==Player.SECOND)) {
                hand1.remove(card)
                hand2.add(card)
            }
            if((card in hand1) and (winner==Player.FIRST)) {
                hand1.remove(card)
                hand1.add(card)
            }
            if((card in hand2) and (winner==Player.FIRST)) {
                hand2.remove(card)
                hand1.add(card)
            }
            if((card in hand2) and (winner==Player.SECOND)) {
                hand2.remove(card)
                hand2.add(card)
            }
        }
        print("\n\n$hand1\n$hand2")
    }
    if (hand1!!.isNotEmpty()) return Player.FIRST
    return Player.SECOND
}

fun main() {
    val deck = fullDeck()
    val hands = deal(deck) // Подумайте, почему val?
    val winner = game(hands)
    println("Победитель: $winner")
}