package solutions

import kotlin.system.measureTimeMillis

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * What is the 10 001st prime number?
 */

fun naivePrimeCalc(max: Int): List<Long> {
    val list = mutableListOf<Long>()
    var i: Long = 1
    while (i < max) {
        if (i.isPrime()) {
            list.add(i)
            i++
        }
    }
    return list
}

// Int/Long div automatically for floor func
fun generateAPrime(n: Long): Long {
    val zaehler = n.faculty() % (n + 1)
    val gerundet = zaehler / n

    return gerundet * (n - 1) + 2
}

fun sieveOfEratosthenes(min: Long, max: Long): List<Long> {
    var values = LongArray((max - min).toInt()) { i -> i + min }
    var index = 0

    val list = mutableListOf<Long>()

    // find the first prime number
    for (i in 0..values.size) {
        if (values[i].isPrime()) {
            list.add(values[i])
            index = i
            break
        } else {
            // mark field as "not prime"
            values[i] = -1
        }
    }

    var p: Long = values[index]

    while (index < values.size) {

        // mark all multiples of this field
        for (i in index until values.size) {
            if (values[i] % p == 0.toLong()) {
                values[i] = (-1).toLong()
            }
        }

        // find index for the next unmarked field
        while (index < values.size && values[index] == (-1).toLong()) {
            index++
        }

        // next unmarked field is our next prime number
        if (index < values.size) {
            p = values[index]
            list.add(p)
        } else {
            // otherwise we have reached the end
            break
        }
    }

    return list
}

// not entirely correct method, as we can't start at a saved index and use firstOrNull
fun sieveOfEratosthenesKotlin(min: Long, max: Long): List<Long> {
    var values = LongArray((max - min).toInt()) { i -> i + min }.toList()
    val primes = mutableListOf<Long>()

    var p: Long? = values.firstOrNull { it.isPrime() } ?: return emptyList()

    primes.add(p!!)
    values = values.filter { it % p!! != 0.toLong() }

    while (true) {
        p = values.firstOrNull { it.isPrime() } ?: break
        primes.add(p)
        values = values.filter { it % p != 0.toLong() }
    }

    return primes

}

// TODO compute this in parallel
fun getSoManyPrimes(somany: Long): List<Long> {
    val primes = mutableListOf<Long>()

    val e: Long = 100
    var currentMin: Long = 0

    while (primes.size < somany) {

        primes.addAll(sieveOfEratosthenesKotlin(currentMin, currentMin + e))
        currentMin += e
        println("updated primes (${primes.size})")

    }

    return primes
}


fun main(args: Array<String>) {
    val min: Long = 0
    val max: Long = 10100
    val index = 10001

    val runningOutOfNames = measureTimeMillis {

        println("calculating $max prime numbers...")
        val soManyPrimes = getSoManyPrimes(max)
        println("calculated primes: ${soManyPrimes.joinToString()}")

        println("The $index'st prime is ${soManyPrimes[index-1]}")


    }

    println("Took $runningOutOfNames ms to calculate $max primes")

    /*
    val eNorm = measureTimeMillis {

        println("calculating all primes from $min to $max")
        println("calculated primes: ${sieveOfEratosthenes(min, max).joinToString()}")


    }

    val eKotlin = measureTimeMillis {

        println("calculating all primes from $min to $max")
        println("calculated primes: ${sieveOfEratosthenesKotlin(min, max).joinToString()}")


    }

    println("normal calculation took $eNorm ms")
    println("kotlin calculation took $eKotlin ms")
    */
}