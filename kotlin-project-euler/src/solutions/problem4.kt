package solutions

import kotlin.math.pow
import kotlin.system.measureTimeMillis

/**
 * A palindromic number reads the same both ways.
 * The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */

fun Int.isPalindrome(): Boolean {
    val stringRep = this.toString()

    var start = 0
    var end = stringRep.length - 1

    while (start <= end) {

        if (stringRep[start] != stringRep[end]) {
            return false
        }
        start++
        end--
    }

    return true
}

fun findLargestPalindrome(len: Int): Int {
    val upperLim = 10.toFloat().pow(len)

    var a: Int = (upperLim - 1).toInt()
    var b: Int = (upperLim - 1).toInt()

    val list = mutableListOf<Int>()

    while (a > 0) {
        while (b > 0) {

            if ((a * b).isPalindrome()) {
                list.add(a * b)
                //println("$a x $b = ${a*b}")
            }
            b--
        }
        b = (upperLim - 1).toInt()
        a--
    }

    return list.max()!!

}

fun findLargestPalindromeAlt(lim: Int): Int {
    val upperLim = 10.toFloat().pow(lim).toInt()
    val lowerLim = 10.toFloat().pow(lim - 1).toInt()

    var i = 0
    val vals = IntArray((upperLim - 1) * (upperLim - 1)) { i++ }.filter {
        it.isPalindrome() &&
                it.getBiggestDiv(lim) in lowerLim until upperLim &&
                it / it.getBiggestDiv(lim) in lowerLim until upperLim
    }

    return vals.max()!!
}

fun Int.getBiggestDiv(len: Int): Int {
    var i = 10.toFloat().pow(len).toInt() - 1

    while (i > 0) {
        if (this % i == 0) {
            return i
        }
        i--
    }

    return -1
}

fun main(args: Array<String>) {

    val len = 3

    val measuredTime = measureTimeMillis {
        println("largest palindrome of length $len is ${findLargestPalindrome(len)}")
    }

    val measuredTimeAlt = measureTimeMillis {
        println("largest palindrome of length $len is ${findLargestPalindromeAlt(len)}")
    }

    println("Took $measuredTime to calculate")
    println("Took $measuredTimeAlt to calculate alternatively")

}