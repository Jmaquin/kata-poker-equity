package poker.kata.domain

interface Board {
    val flop: Flop
    val turn: Card
    val river: Card
}

class FlopBoard(override val flop: Flop) : Board {
    override val turn: Card = Card.createWhiteCard()
    override val river: Card = Card.createWhiteCard()
}

class TurnBoard(override val flop: Flop, override val turn: Card) : Board {
    override val river: Card = Card.createWhiteCard()
}

class RiverBoard(override val flop: Flop, override val turn: Card, override val river: Card) : Board