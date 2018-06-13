package poker.kata.domain

import io.kotlintest.matchers.beInstanceOf
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.WordSpec

class DeckShould : WordSpec() {
    init {
        "Deck" should {
            "create a list of 52 different cards" {
                //Given

                //When
                val deck = Deck()

                //Then
                deck.size() shouldBe 52
                deck.getCards().distinct().size shouldBe 52
            }

            "draw a card and remove it from deck" {
                //Given
                val deck = Deck()

                //When
                val result = deck.drawCard()

                //Then
                result shouldBe beInstanceOf(Card::class)
                deck.size() shouldBe 51
            }
        }
    }
}