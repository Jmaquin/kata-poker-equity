package poker.kata.domain

import io.kotlintest.matchers.beInstanceOf
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.WordSpec
import poker.kata.exception.GameAlreadyFinishedException

class GameShould : WordSpec() {
    init {
        "Game" should {
            "have state EmptyGameState when game starts" {
                //Given

                //When
                val game = Game()

                //Then
                game.currentState shouldBe beInstanceOf(EmptyGameState::class)
                game.deck.size() shouldBe 52
                game.communityCards.flop shouldBe null
                game.communityCards.turn shouldBe null
                game.communityCards.river shouldBe null
            }
            "have state FlopGameState when game is at flop stage" {
                //Given
                val game = Game()

                //When
                game.nextTurn()

                //Then
                game.currentState shouldBe beInstanceOf(FlopGameState::class)
                game.deck.size() shouldBe 49
                game.communityCards.flop shouldBe beInstanceOf(Flop::class)
                game.communityCards.turn shouldBe null
                game.communityCards.river shouldBe null
            }
            "have state TurnGameState when game is at turn stage" {
                //Given
                val game = Game()
                game.nextTurn() //Game at flop stage

                //When
                game.nextTurn()

                //Then
                game.currentState shouldBe beInstanceOf(TurnGameState::class)
                game.deck.size() shouldBe 48
                game.communityCards.flop shouldNotBe null
                game.communityCards.turn shouldNotBe null
                game.communityCards.river shouldBe null
            }
            "have state RiverGameState when game is at river stage" {
                //Given
                val game = Game()
                game.nextTurn() //Game at flop stage
                game.nextTurn() //Game at turn stage

                //When
                game.nextTurn()

                //Then
                game.currentState shouldBe beInstanceOf(RiverGameState::class)
                game.deck.size() shouldBe 47
                game.communityCards.flop shouldNotBe null
                game.communityCards.turn shouldNotBe null
                game.communityCards.river shouldNotBe null
            }
            "throw GameAlreadyFinishedException when game is already at river stage" {
                //Given
                val game = Game()
                game.nextTurn() //Game at flop stage
                game.nextTurn() //Game at turn stage
                game.nextTurn() //Game at river stage

                //When
                shouldThrow<GameAlreadyFinishedException> { game.nextTurn() }

                //Then
            }
        }
    }
}