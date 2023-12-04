package com.jpaoletti.aoc.y2023

import com.jpaoletti.aoc.inputLines


// https://adventofcode.com/2023
fun main() {
    println("Day 1:")
    println()

//    pt1()
    println()

    pt2()
    println()
}

private fun extractNum(line: String): Int {
    var firstDig: Char? = null
    var lastDig: Char? = null

    line.forEach { c ->
        if (c.isDigit()) {
            if (firstDig == null) {
                firstDig = c
            }
            lastDig = c
        }
    }

    return firstDig!!.digitToInt() * 10 + lastDig!!.digitToInt()
}

private fun pt1() {
    println("Part 1:")
    val lines = inputLines(year = 2023, day = 1, part = 1)
    val sum = lines.map(::extractNum).sum()
    println("Sum: $sum")
}

val strToNum = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
)

val numRegex = "(${strToNum.keys.joinToString("|")}|\\d)".toRegex()

private fun extractNum2(line: String): Int {
    var firstDig: Int? = null
    var lastDig: Int? = null

    var m = numRegex.find(line)
    while (m != null) {
        val v = if (m.value.length == 1) m.value.toInt() else strToNum[m.value]!!

        if (firstDig == null) {
            firstDig = v
        }
        lastDig = v

        m = numRegex.find(line, m.range.first + 1)
    }

    return firstDig!! * 10 + lastDig!!
}
private fun pt2() {
    println("Part 2:")
    val lines = inputLines(year = 2023, day = 1, part = 2)
    val sum = lines.map(::extractNum2).sum()
    println("Sum: $sum")
}
