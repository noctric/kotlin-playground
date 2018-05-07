package solutions

import kotlin.system.measureTimeMillis

/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * a² + b² = c²
 * For example, 3² + 4² = 9 + 16 = 25 = 5².
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */

val predicate = fun(a: Int, b: Int, c: Int): Boolean = b in (a + 1)..(c - 1) &&
        a * a + b * b == c * c

val secondPredicate = fun(a: Int, b: Int, c: Int): Boolean = a + b + c == 1000

data class Triple(val a: Int, val b: Int, val c: Int)

fun Triple.applyPredicate(f: (Int, Int, Int) -> Boolean): Boolean = f(this.a, this.b, this.c)

fun calcPrimitive(): Triple? {
    // generate all possible triples and check if the predicates apply
    for (i in 0 until 1000) {
        for (j in i + 1 until 1000) {
            for (k in j + 1 until 1000) {
                val triple = Triple(i, j, k)
                // check if predicates apply
                if (triple.applyPredicate(secondPredicate)) {
                    if (triple.applyPredicate(predicate)) {
                        return triple
                    }
                }

            }
        }
    }

    return null
}

fun generateTripleEuclid(n: Int, m: Int): Triple {
    if (m <= n || n <= 0) {
        throw IllegalArgumentException("args must be 0 < n < m. Is actually n: $n and m: $m")
    }

    val a = (m * m) - (n * n)
    val b = 2 * m * n
    val c = (m * m) + (n * n)

    return Triple(a, b, c)
}

// can generate ALL possible Triples by adding a factor k
fun generateTripleEuclidAlt(n: Int, m: Int, k: Int): Triple {
    if (m <= n || n <= 0) {
        throw IllegalArgumentException("args must be 0 < n < m. Is actually n: $n and m: $m")
    }

    val a = k * ((m * m) - (n * n))
    val b = k * 2 * m * n
    val c = k * ((m * m) + (n * n))

    return Triple(a, b, c)
}

fun calcByGeneratingValidTriples(): Triple? {
    val max = 1000
    for (i in 1..max) {
        for (j in i + 1..max) {
            val triple = generateTripleEuclid(i, j)
            if (triple.applyPredicate(secondPredicate)) {
                return triple
            }
        }
    }
    return null
}

fun calcByGeneratingValidTriplesAlt(): Triple? {
    val max = 1000
    for (i in 1..max) {
        for (j in i + 1..max) {
            for (k in 1..max) {
                val triple = generateTripleEuclidAlt(i, j, k)
                if (triple.applyPredicate(secondPredicate)) {
                    return triple
                }
            }
        }
    }
    return null
}

fun main(args: Array<String>) {

    /*
    val timePrimitive = measureTimeMillis {

        println("calculating triple...")
        println("result= ${calcPrimitive()}")

    }

    println("took $timePrimitive ms to calculate")

    */

    val timeEuclid = measureTimeMillis {

        println("calculating triple...")
        println("result= ${calcByGeneratingValidTriples()}")

    }

    println("took $timeEuclid ms to calculate")

    val timeEuclidAlt = measureTimeMillis {

        println("calculating triple...")
        println("result= ${calcByGeneratingValidTriplesAlt()}")

    }

    println("took $timeEuclidAlt ms to calculate")

}