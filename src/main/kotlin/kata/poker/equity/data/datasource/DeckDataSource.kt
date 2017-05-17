package kata.poker.equity.data.datasource

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kata.poker.equity.model.Card

class DeckDataSource {
    fun getAllCards(): List<Card> =
            jacksonObjectMapper().readValue(DeckDataSource::class.java.getResource("/data/deck.json").readText())
}