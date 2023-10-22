package com.jpaoletti.aoc.y2015

import com.jpaoletti.aoc.inputLines
import java.security.MessageDigest

fun main() {
    println("Day 4:")
    println()

//    pt1()
//    println()

    pt2()
    println()
}

@OptIn(ExperimentalStdlibApi::class)
private fun computeHash(key: String, num: Long): String {
    val str  = "$key$num"
    val bytes = str.toByteArray()
    val md = MessageDigest.getInstance("MD5")
    return md.digest(bytes).joinToString(separator = "") { b -> b.toHexString() }
}

private fun validateHash(hash: String, zeroCount: Int): Boolean {
    if (hash.length < 5) {
        return false
    }

    for (i in 0 until zeroCount) {
        if (hash[i] != '0') {
            return false
        }
    }

    return true
}

private fun pt1() {
    println("Part 1:")
    val key = inputLines(year = 2015, day = 4, part = 1).first()
    var curr = 1L
    var found = false

    while (!found) {
        val hash = computeHash(key, curr)
        if (validateHash(hash, 5)) {
            found = true
        } else {
            curr++
        }
    }

    println("First positive int $curr")
}

private fun pt2() {
    println("Part 2:")
    val key = inputLines(year = 2015, day = 4, part = 1).first()
    var curr = 1L
    var found = false

    while (!found) {
        val hash = computeHash(key, curr)
        if (validateHash(hash, 6)) {
            found = true
        } else {
            curr++
        }
    }

    println("First positive int $curr")
}
