package com.jpaoletti.aoc.y2015

import com.jpaoletti.aoc.inputLines

// https://adventofcode.com/2015
fun main() {
    println("Day 5:")
    println()

    pt1()
    println()

    pt2()
    println()
}

private val DISALLOW_LIST = setOf(
    "ab",
    "cd",
    "pq",
    "xy"
)

private val VOWELS = setOf( 'a', 'e', 'i', 'o', 'u' )

private fun isNice(str: String): Boolean {
    var vowelCount = 0
    var hasDouble = false
    var noDisallows = true

    for (i in 0 until str.length - 1) {
        if (VOWELS.contains(str[i])) {
            vowelCount++
        }

        if (str[i] == str[i + 1]) {
            hasDouble = true
        }

        if (DISALLOW_LIST.contains(str.substring(i, i + 2))) {
            noDisallows = false
            break
        }
    }

    if (VOWELS.contains(str.last())) {
        vowelCount++
    }

    return vowelCount >= 3 && hasDouble && noDisallows
}

private fun pt1() {
    println("Part 1:")
    val input = inputLines(year = 2015, day = 5, part = 1)

    val niceCount = input.map { if (isNice(it)) 1 else 0 }.sum()
    println("Nice count: $niceCount")
}

private fun isNice2(str: String): Boolean {
    var hasPairNoOverlaps = false
    var hasSammy = false

    val pairsWithIndex = mutableMapOf<String, Int>()

    for ((idx, sub) in str.windowedSequence(2).withIndex()) {
        if (!pairsWithIndex.containsKey(sub)) {
            pairsWithIndex[sub] = idx
        } else {
            val existingIdx = pairsWithIndex[sub]!!
            if (idx - existingIdx > 1) {
                hasPairNoOverlaps = true
                break
            }
        }
    }

    for (sub in str.windowedSequence(3 )) {
        if (sub[0] == sub[2]) {
            hasSammy = true
            break
        }
    }

    return hasPairNoOverlaps && hasSammy
}
private fun pt2() {
    println("Part 2:")
    val input = inputLines(year = 2015, day = 5, part = 1)

    val niceCount = input.map { if (isNice2(it)) 1 else 0 }.sum()
    println("Nice count: $niceCount")
}
