package lang

class Reader<C : Any, out A : Any>(val run: (C) -> A) {

    inline fun <B : Any> map(crossinline fa: (A) -> B): Reader<C, B> = Reader { c -> fa(run(c)) }

    inline fun <B : Any> flatMap(crossinline fa: (A) -> Reader<C, B>): Reader<C, B> = Reader { c ->
        fa(run(c)).run(c)
    }

    fun <B : Any> zip(other: Reader<C, B>): Reader<C, Pair<A, B>> =
            this.flatMap { a ->
                other.map { b -> Pair(a, b) }
            }

    /**
     * local combinator allows switching the environment to unify two different dependency types, so
     * you can compose readers with different type dependencies.
     *
     * D: type represents a different context than C.
     * @param fd: function to convert from a D context to a context of type C.
     */
    inline fun <B : Any> local(crossinline fd: (B) -> C): Reader<B, A> = Reader { b ->
        run(fd(b))
    }

    companion object Factory {

        /**
         * Lifts an A value to Reader wrapping it in a supplier function with a Nothing argument.
         */
        fun <C : Any, A : Any> pure(a: A): Reader<C, A> = Reader { _ -> a }

        fun <C : Any> ask(): Reader<C, C> = Reader { it }
    }
}

fun <A : Any, B : Any> ((A) -> B).reader() : Reader<A, B> = Reader(this)