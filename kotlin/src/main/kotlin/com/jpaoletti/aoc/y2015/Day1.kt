package com.jpaoletti.aoc.y2015

import com.jpaoletti.aoc.inputLines

fun main() {
    println("Day 1")
    pt1()
    pt2()
}

fun pt2() {
    println("Part 2")
    val input = inputLines(year = 2015, day = 1, part = 1).first() // part 2 uses same input

    var currFloor = 0
    var place = 1
    for (c in input) {
        when (c) {
            '(' -> currFloor++
            ')' -> currFloor--
        }

        if (currFloor < 0) {
            println("Enters basement at $place")
            break
        }

        place++
    }
}

fun pt1() {
    println("Part 1")
    val input = inputLines(year = 2015, day = 1, part = 1).first()

    var currFloor = 0
    input.forEach { c ->
        when (c) {
            '(' -> currFloor++
            ')' -> currFloor--
        }
    }

    println("Floor $currFloor")
}
