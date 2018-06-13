package poker.kata.data.datasource

import io.kotlintest.matchers.beInstanceOf
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.specs.WordSpec
import poker.kata.domain.Card

class DeckDatasourceTest : WordSpec() {
    init {
        "DeckDatasource" should {
            "map the json content to a list of cards" {
                //Given
                val deckDataSource = DeckDataSource()

                //When
                val allCards = deckDataSource.getAllCards()

                //Then
                allCards shouldBe beInstanceOf(List::class)
                allCards.forEach { it shouldBe beInstanceOf(Card::class) }
                allCards shouldNotBe emptyList<Card>()
                allCards.size shouldBe 52
            }
        }
    }
}