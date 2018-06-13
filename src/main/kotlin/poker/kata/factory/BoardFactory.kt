package poker.kata.factory

import poker.kata.domain.*

abstract class BoardFactory {
    companion object {
        fun  createBoard(flop : Flop, vararg cards: Card): Board = when (cards.size) {
            0 -> FlopBoard(flop)
            1 -> TurnBoard(flop, cards[0])
            2 -> RiverBoard(flop, cards[0], cards[1])
            else -> throw IllegalArgumentException("Invalid number of cards")
        }
    }
}