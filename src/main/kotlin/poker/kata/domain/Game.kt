package poker.kata.domain

import poker.kata.exception.GameAlreadyFinishedException

interface GameState {
    fun nextTurn(): GameState
}

class Game {
    val deck = Deck()
    val communityCards = CommunityCards()
    var currentState: GameState = EmptyGameState(this)
        private set

    fun nextTurn() {
        currentState = currentState.nextTurn()
    }
}

class EmptyGameState(private val game: Game) : GameState {
    override fun nextTurn(): GameState {
        game.communityCards.addFlop(game.deck.drawCard(), game.deck.drawCard(), game.deck.drawCard())
        return FlopGameState(game)
    }
}

class FlopGameState(private val game: Game) : GameState {
    override fun nextTurn(): GameState {
        game.communityCards.addTurn(game.deck.drawCard())
        return TurnGameState(game)
    }
}

class TurnGameState(private val game: Game) : GameState {
    override fun nextTurn(): GameState {
        game.communityCards.addRiver(game.deck.drawCard())
        return RiverGameState()
    }
}

class RiverGameState : GameState {
    override fun nextTurn(): GameState {
        throw GameAlreadyFinishedException("Game finished.Can't go to next turn")
    }
}