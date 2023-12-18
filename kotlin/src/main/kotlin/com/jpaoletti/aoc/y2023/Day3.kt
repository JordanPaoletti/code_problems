package com.jpaoletti.aoc.y2023

import com.jpaoletti.aoc.inputLines
import com.jpaoletti.aoc.inputLinesEx

// https://adventofcode.com/2023
fun main() {
    println("Day 3:")
    println()

    pt1()
    println()

    pt2()
    println()
}

private fun parseInput(lines: List<String>): List<List<Char>> = lines.map { it.toList() }

private fun List<List<*>>.inBounds(x: Int, y: Int): Boolean {
    return (y >= 0 && y < this.size) && (x >= 0 && x < this[0].size)
}

private fun <T> List<List<T>>.checkNeighbors(x: Int, y: Int, func: (T) -> Boolean): Boolean {
    for (i in x-1..x+1) {
        if (this.inBounds(i, y - 1)) {
            if (func.invoke(this[y-1][i])) {
                return true
            }
        }
    }

    if (this.inBounds(x - 1, y)) {
        if (func.invoke(this[y][x - 1])) {
            return true
        }
    }

    if (this.inBounds(x + 1, y)) {
        if (func.invoke(this[y][x + 1])) {
            return true
        }
    }

    for (i in x-1..x+1) {
        if (this.inBounds(i, y + 1)) {
            if (func.invoke(this[y+1][i])) {
                return true
            }
        }
    }

    return false
}

private fun isSymbol(c: Char): Boolean = c != '.' && !c.isDigit()

private fun pt1() {
    println("Part 1:")
//    val input = inputLinesEx(year = 2023, day = 3)
    val input = inputLines(year = 2023, day = 3, part = 1)
    val spec = parseInput(input)

    var sum: Long = 0
    for (y in spec.indices) {
        var numStart: Int? = null
        var isPart: Boolean = false
        for (x in spec[0].indices) {
            val c = spec[y][x]
            if (c.isDigit()) {
                if (numStart == null) {
                    numStart = x
                }
                if (!isPart) {
                    isPart = spec.checkNeighbors(x, y, ::isSymbol)
                }
            } else if (numStart != null) {
                if (isPart) {
                    val num = String(spec[y].subList(numStart, x).toCharArray()).toLong()
                    println(num)
                    sum += num
                }
                isPart = false
                numStart = null
            }
        }

        if (numStart != null && isPart) {
            val num = String(spec[y].subList(numStart, spec[y].size).toCharArray()).toLong()
            sum += num
        }
    }

    println("Sum: $sum")
}

private fun <T> List<List<T>>.findNeighbor(x: Int, y: Int, func: (T) -> Boolean): Pair<Int, Int>? {
    for (i in x-1..x+1) {
        if (this.inBounds(i, y - 1)) {
            if (func.invoke(this[y-1][i])) {
                return i to y - 1
            }
        }
    }

    if (this.inBounds(x - 1, y)) {
        if (func.invoke(this[y][x - 1])) {
            return x - 1 to y
        }
    }

    if (this.inBounds(x + 1, y)) {
        if (func.invoke(this[y][x + 1])) {
            return x + 1 to y
        }
    }

    for (i in x-1..x+1) {
        if (this.inBounds(i, y + 1)) {
            if (func.invoke(this[y+1][i])) {
                return i to y + 1
            }
        }
    }

    return null
}

private fun pt2() {
    println("Part 2:")
    val input = inputLinesEx(year = 2023, day = 3)
//    val input = inputLines(year = 2023, day = 3, part = 1)
    val spec = parseInput(input)

    var sum: Long = 0

    for (y in spec.indices) {
        var numStart: Int? = null
        var isPart: Boolean = false
        for (x in spec[0].indices) {
            val c = spec[y][x]
            if (c.isDigit()) {
                if (numStart == null) {
                    numStart = x
                }
                if (!isPart) {
                    isPart = spec.checkNeighbors(x, y, ::isSymbol)
                }
            } else if (numStart != null) {
                if (isPart) {
                    val num = String(spec[y].subList(numStart, x).toCharArray()).toLong()
                    println(num)
                    sum += num
                }
                isPart = false
                numStart = null
            }
        }

        if (numStart != null && isPart) {
            val num = String(spec[y].subList(numStart, spec[y].size).toCharArray()).toLong()
            sum += num
        }
    }

    println("Sum: $sum")
}
