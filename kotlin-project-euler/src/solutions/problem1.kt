package solutions

import kotlin.system.measureTimeMillis

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
 * The sum of these multiples is 23.
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */

fun Int.isMultipleOfThreeOrFive(): Boolean = (this % 3 == 0 || this % 5 == 0)

//region naive solution
fun getSum(max: Int): Int {
    var index = 3
    var sum = 0
    while (index < max) {
        if (index.isMultipleOfThreeOrFive()) {
            sum += index
        }
        index++
    }

    return sum
}
//endregion

//region using kotlin syntax
fun getSumAlt(max: Int): Int =
    IntArray(max) { i -> i }.filter { it.isMultipleOfThreeOrFive() }.sum()
//endregion

//region a different approach

// Does not consider double multiples :/
fun getSumDif(max: Int): Int = IntArray(max / 3) {i -> i * 3}.sum() + IntArray(max / 5) {i -> i * 5}.sum()
//endregion

fun main(args: Array<String>) {
    val max = 1000

    val measuredTimeNaive = measureTimeMillis {
        println("The sum of all multiples of 3 and 5 in $max is ${getSum(max)}")
    }

    val measuredTimeKotlin = measureTimeMillis {
        println("The sum of all multiples of 3 and 5 in $max is ${getSumAlt(max)}")
    }

    val measuredTimeDif = measureTimeMillis {
        println("The sum of all multiples of 3 and 5 in $max is ${getSumDif(max)}")
    }

    println("naive took $measuredTimeNaive")
    println("kotlin took $measuredTimeKotlin")
    println("dif took $measuredTimeDif")
}