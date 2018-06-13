package poker.kata.domain

class CommunityCards {
    var flop: Flop? = null
        private set
    var turn: Card? = null
        private set
    var river: Card? = null
        private set

    fun addFlop(firstCard: Card, secondCard: Card, thirdCard: Card) {
        flop = Flop(firstCard, secondCard, thirdCard)
    }

    fun addTurn(card: Card) {
        turn = card
    }

    fun addRiver(card: Card) {
        river = card
    }
}