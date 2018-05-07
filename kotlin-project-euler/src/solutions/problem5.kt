package solutions

import kotlin.math.abs
import kotlin.system.measureTimeMillis

/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */

fun Long.faculty(): Long {
    var res = 1.toLong()
    var i = 1.toLong()
    while (i <= this) {
        res *= i
        i++
    }

    return res
}

fun Long.getSmallestDivisibleNumber(): Long {
    var tmp: Long = 1

    for (i in 1..this) {
        println("least common multiple of $tmp and $i is ")
        tmp = tmp.lcm(i)
        println(" = $tmp")
    }

    return tmp
}

fun Long.lcm(other: Long): Long = abs(this * other) / this.gcd(other)

fun Long.gcd(other: Long): Long {
    var h: Long
    var a = this
    var b = other

    if (a == 0.toLong()) {
        return abs(other)
    }

    if (b == 0.toLong()) {
        return abs(a)
    }

    do {
        h = a % b
        a = b
        b = h
    } while (b != 0.toLong())

    return abs(a)
}

fun main(args: Array<String>) {

    val max: Long = 20

    val measuredTime = measureTimeMillis {
        println("smallest dividable number by all numbers leading up to $max is ${max.getSmallestDivisibleNumber()}")
    }

    println("Took $measuredTime to calculate")
    println("$max! = ${max.toLong().faculty()}")
}