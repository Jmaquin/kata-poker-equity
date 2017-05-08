package kata.poker.equity.factory

import io.kotlintest.matchers.beInstanceOf
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.WordSpec
import kata.poker.equity.model.*

class BoardFactoryTest : WordSpec() {

    init {
        "Board factory" should {
            "return a FlopBoard when createBoard is called with a flop only" {
                //Given
                val flop = Flop(Card.createWhiteCard(), Card.createWhiteCard(), Card.createWhiteCard())

                //When
                val flopBoard = BoardFactory.createBoard(flop)

                //Then
                flopBoard shouldBe beInstanceOf(FlopBoard::class)
            }

            "return a TurnBoard when createBoard is called with a flop and one card" {
                //Given
                val flop = Flop(Card.createWhiteCard(), Card.createWhiteCard(), Card.createWhiteCard())
                val card = Card.createWhiteCard()

                //When
                val turnBoard = BoardFactory.createBoard(flop, card)

                //Then
                turnBoard shouldBe beInstanceOf(TurnBoard::class)
            }

            "return a RiverBoard when createBoard is called with a flop and two cards" {
                //Given
                val flop = Flop(Card.createWhiteCard(), Card.createWhiteCard(), Card.createWhiteCard())
                val firstCard = Card.createWhiteCard()
                val secondCard = Card.createWhiteCard()

                //When
                val riverBoard = BoardFactory.createBoard(flop, firstCard, secondCard)

                //Then
                riverBoard shouldBe beInstanceOf(RiverBoard::class)
            }

            "throw IllegalArgumentException when createBoard is called with a flop and more than two cards" {
                //Given
                val flop = Flop(Card.createWhiteCard(), Card.createWhiteCard(), Card.createWhiteCard())
                val firstCard = Card.createWhiteCard()
                val secondCard = Card.createWhiteCard()
                val thirdCard = Card.createWhiteCard()

                //When
                val exception = shouldThrow<IllegalArgumentException> {
                    BoardFactory.createBoard(flop, firstCard, secondCard, thirdCard)
                }

                //Then
                exception.message shouldBe "Invalid number of cards"
            }
        }
    }
}