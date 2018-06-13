package poker.kata.domain

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.WordSpec
import poker.kata.enums.Rank
import poker.kata.enums.Suit

class CommunityCardsShould : WordSpec() {
    init {
        "CommunityCards" should {
            "create the flop" {
                //Given
                val aFirstCard = Card(Suit.CLUBS, Rank.ACE)
                val aSecondCard = Card(Suit.CLUBS, Rank.TWO)
                val aThirdCard = Card(Suit.CLUBS, Rank.THREE)
                val communityCards = CommunityCards()

                //When
                communityCards.addFlop(aFirstCard, aSecondCard, aThirdCard)

                //Then
                communityCards.flop?.firstCard shouldBe aFirstCard
                communityCards.flop?.secondCard shouldBe aSecondCard
                communityCards.flop?.thirdCard shouldBe aThirdCard
            }
            "create the turn" {
                //Given
                val aCard = Card(Suit.CLUBS, Rank.ACE)
                val communityCards = CommunityCards()

                //When
                communityCards.addTurn(aCard)

                //Then
                communityCards.turn shouldBe aCard
            }
            "create the river" {
                //Given
                val aCard = Card(Suit.CLUBS, Rank.ACE)
                val communityCards = CommunityCards()

                //When
                communityCards.addRiver(aCard)

                //Then
                communityCards.river shouldBe aCard
            }
        }
    }
}