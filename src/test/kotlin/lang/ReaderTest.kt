package lang

import io.kotlintest.matchers.beInstanceOf
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.WordSpec

class ReaderTest : WordSpec() {
    init {
        "Reader" should {
            "return the value passed as parameter when pure function is called and reader is launched with a random context" {
                //Given
                val aValue = 1
                val aContext = "aContext"

                //When
                val result = Reader.pure<String, Int>(aValue).run(aContext)

                //Then
                result shouldBe aValue
            }

            "return a value with the same type as context when ask function is called and reader is launched with the context" {
                //Given
                val aContext = 1

                //When
                val result = Reader.ask<Int>().run(aContext)

                //Then
                result shouldBe aContext
            }

            "return a reader when map is applied on a reader that return a reader" {
                //Given
                val aContext = 1
                val aReader = Reader.ask<Int>().map { it * 2 }

                //When
                val result = Reader.ask<Int>()
                        .map { aReader }
                        .run(aContext)

                //Then
                result shouldBe aReader
                result.run(aContext) shouldBe 2
            }

            "return value when flatMap is applied on a reader that return a reader" {
                //Given
                val aContext = 1
                val aReader = Reader.ask<Int>().map { it * 2 }

                //When
                val result = Reader.ask<Int>()
                        .flatMap { aReader }
                        .run(aContext)

                //Then
                result shouldBe 2
            }

            "return a Pair of the two readers values when zip is applied on a the first reader with the second reader as parameter and reader is launched with context" {
                //Given
                val aContext = 1
                val aFirstReader = Reader.ask<Int>().map { it * 2 }
                val aSecondReader = Reader.ask<Int>().map { it * 3 }

                //When
                val result = aFirstReader
                        .zip(aSecondReader)
                        .run(aContext)

                //Then
                result shouldBe Pair(2, 3)
            }

            "return value when local is applied and reader is launched with a context of different type than the context expected" {
                //Given
                val aContext = "1"

                //When
                val result = Reader.ask<Int>()
                        .local<String> { it.toInt() }
                        .run(aContext)

                //Then
                result shouldBe 1
            }
            "return a Reader instance when reader function is called on a lambda" {
                //Given
                val aContext = 1
                val aLambda = { value: Int -> value + 1 }

                //When
                val result = aLambda.reader()

                //Then
                result shouldBe beInstanceOf(Reader::class)
                result.run(aContext) shouldBe 2
            }
        }
    }
}