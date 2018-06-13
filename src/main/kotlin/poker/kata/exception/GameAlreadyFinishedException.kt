package poker.kata.exception

class GameAlreadyFinishedException(override var message: String) : Exception(message)