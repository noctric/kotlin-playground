package solutions

import kotlin.system.measureTimeMillis

/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 */


fun getSumOfPrimes(max: Long): Long {
    val primes = mutableListOf<Long>()

    val e: Long = 100
    var currentMin: Long = 0

    while (currentMin + e <= max) {

        println("finding all primes from $currentMin to ${currentMin + e}")
        primes.addAll(sieveOfEratosthenesKotlin(currentMin, currentMin + e))
        currentMin += e
        println("updated primes (${primes.size})")

    }

    return primes.sum()
}

fun main(args: Array<String>) {

    val max: Long = 2000000

    val time = measureTimeMillis {

        println("The sum of all primes below $max is ${getSumOfPrimes(max)}")

    }

    println("Took $time ms to calculate")
}