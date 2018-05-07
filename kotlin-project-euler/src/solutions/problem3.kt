package solutions

import kotlin.system.measureTimeMillis

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143 ?
 */

fun Long.isPrime(): Boolean {
    if (this <= 1)
        return false
    else if (this <= 3)
        return true
    else if (this % 2 == 0.toLong() || this % 3 == 0.toLong())
        return false
    var i = 5
    while (i * i <= this) {
        if (this % i == 0.toLong() || this % (i + 2) == 0.toLong())
            return false
        i += 6
    }
    return true
}

fun Long.getLargestPrimeFactor(): Long {

    if (this.isPrime()) {
        return this
    }

    var firstPrime: Long = 2

    while (firstPrime < this) {

        if (this % firstPrime == 0.toLong()) {

            if (firstPrime.isPrime()) {

                return (this / firstPrime)

            }

        }

        firstPrime++

    }

    return -1

}

fun main(args: Array<String>) {
    val value: Long = 600851475143

    val measuredTime = measureTimeMillis {
        println("The largest prime factor of $value is ${value.getLargestPrimeFactor()}")
    }

    println("Took $measuredTime to calculate")
}