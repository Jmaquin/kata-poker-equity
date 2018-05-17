package poker.kata.model

data class Card(val code: String, val suit: String, val value: Int) {
    companion object {
        fun createWhiteCard(): Card {
            return Card("", "WHITE CARD", -1)
        }
    }
}