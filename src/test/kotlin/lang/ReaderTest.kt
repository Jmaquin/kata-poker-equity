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

            "return modified value when map is applied and reader is launched with context" {
                //Given
                val aContext = listOf(1)

                //When
                val result = Reader.ask<List<Int>>()
                        .map { it.map { listOf(it * 2) } }
                        .run(aContext)

                //Then
                result shouldBe beInstanceOf(List::class)
                result.size shouldBe 1
                result[0] shouldBe listOf(2)
            }

            "return modified value when flatMap is applied and reader is launched with context" {
                //Given
                val aContext = listOf(1)

                //When
                val result = Reader.ask<List<Int>>()
                        .map { it.flatMap { listOf(it * 2) } }
                        .run(aContext)

                //Then
                result shouldBe beInstanceOf(List::class)
                result.size shouldBe 1
                result[0] shouldBe 2
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

            "return modified value when local is applied and reader is launched with a context of different type than the context expected" {
                //Given
                val aContext = "1"

                //When
                val result = Reader.ask<Int>()
                        .local<String> { it.toInt() }
                        .map { it + 1 }
                        .map { it + 2 }
                        .run(aContext)

                //Then
                result shouldBe 4
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