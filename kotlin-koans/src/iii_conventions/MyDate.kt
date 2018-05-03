package iii_conventions

import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) {
}

operator fun MyDate.inc(): MyDate = this.nextDay()

operator fun MyDate.plus(interval: TimeInterval): MyDate = this.addTimeIntervals(interval, 1)

operator fun MyDate.plus(repeatedTimeInterval: RepeatedTimeInterval): MyDate =
        this.addTimeIntervals(repeatedTimeInterval.interval, repeatedTimeInterval.reps)

operator fun MyDate.compareTo(other: MyDate): Int = when {
    this.year != other.year -> this.year - other.year
    this.month != other.month -> this.month - other.month;
//this.dayOfMonth != other.dayOfMonth -> this.dayOfMonth - other.dayOfMonth
    else -> {
        this.dayOfMonth - other.dayOfMonth
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

enum class TimeInterval {

    DAY,
    WEEK,
    YEAR
}

operator fun TimeInterval.times(reps: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, reps)

class RepeatedTimeInterval(val interval: TimeInterval, val reps: Int) {
}

class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = MyDateIterator(this)
}

class MyDateIterator(val dateRange: DateRange) : Iterator<MyDate> {
    var currentDate: MyDate = dateRange.start

    override fun hasNext(): Boolean = dateRange.endInclusive >= currentDate

    override fun next(): MyDate {
        val currentVal = currentDate
        currentDate++
        return currentVal
    }

}

operator fun DateRange.contains(date: MyDate): Boolean =
        this.start <= date && this.endInclusive >= date