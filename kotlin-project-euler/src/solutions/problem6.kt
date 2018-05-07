package solutions

import kotlin.math.abs
import kotlin.math.pow
import kotlin.system.measureTimeMillis

/**
 * The sum of the squares of the first ten natural numbers is
 * 1² + 2² + ... + 10² = 385
 * The square of the sum of the first ten natural numbers is
 * (1 + 2 + ... + 10)² = 552 = 3025
 * Hence the difference between the sum of the squares of the first ten natural numbers and
 * the square of the sum is 3025 − 385 = 2640.
 * Find the difference between the sum of the squares of the first one hundred natural numbers and
 * the square of the sum.
 */

fun getSumOfNaturalNumbersNaive(max: Int): Int {
    var sum = 0
    var i = 0
    while (i < max) {
        sum += i
        i++
    }

    return sum
}

fun getSumOfNaturalNumbers(max: Int): Int = IntArray(max) { i -> i }.sum()

fun getSumGauss(max: Int) = max * (max + 1) / 2

fun squareSumOfNaturalNumbers(max: Int): Long {
    var sum:Double = 0.0
    var i = 0.0
    while (i <= max) {
        sum += i.pow(2)
        i++
    }

    return sum.toLong()
}

fun main(args: Array<String>) {
    val max = 100
    val sumOfSquaredNumbers = squareSumOfNaturalNumbers(max)
    val sumToSqaure = getSumGauss(max).toDouble().pow(2).toLong()
    println("The difference between the square of the first $max natural numbers and the " +
            "square sum of the first $max natural numbers is |$sumOfSquaredNumbers - $sumToSqaure| = " +
            "${abs(sumOfSquaredNumbers-sumToSqaure)}")
}