package poker.kata.domain

import poker.kata.enums.Rank
import poker.kata.enums.Suit
import java.util.*

class Deck {
    private var deck = mutableListOf<Card>()

    init {
        deck.addAll(Suit.values().flatMap { suit -> Rank.values().map { rank -> Card(suit, rank) } })
        Collections.shuffle(deck)
    }

    fun size(): Int {
        return deck.size
    }

    fun getCards(): List<Card> {
        return deck
    }

    fun drawCard(): Card {
        val drawnCard = deck[0]
        deck.removeAt(0)
        return drawnCard
    }
}