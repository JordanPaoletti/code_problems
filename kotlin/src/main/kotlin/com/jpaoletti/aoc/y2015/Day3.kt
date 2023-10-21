package com.jpaoletti.aoc.y2015

import com.jpaoletti.aoc.inputLines
import java.lang.IllegalArgumentException

data class Point(val x: Int, val y: Int) {
    operator fun plus(o: Point): Point = Point(x = x + o.x, y = y + o.y)
}

enum class Direction(val str: Char, val unitVector: Point) {
    NORTH('^', Point(0, 1)),
    SOUTH('v', Point(0, -1)),
    WEST('<', Point(-1, 0)),
    EAST('>', Point(1, 0));

    companion object {
        @JvmStatic
        val directionByChar = entries.associateBy(Direction::str)

        @JvmStatic
        fun fromChar(char: Char): Direction {
            return directionByChar[char] ?: throw IllegalArgumentException("Unknown direction")
        }
    }
}

fun main() {
    println("Day 3:")
    println()

//    pt1()
//    println()

    pt2()
    println()
}

private fun pt1() {
    println("Part 1:")
    val input = inputLines(year = 2015, day = 3, part = 1).first()

    val visited = mutableSetOf<Point>()
    var currPos: Point = Point(0, 0)

    input.forEach { dirStr ->
        visited.add(currPos)

        val dir = Direction.fromChar(dirStr)
        currPos += dir.unitVector
    }

    println("Visited ${visited.size} unique homes")
}

private fun pt2() {
    println("Part 2:")
    val input = inputLines(year = 2015, day = 3, part = 1).first()

    val visited = mutableSetOf<Point>()
    var currPos = Point(0, 0)
    var roboPos = Point(0, 0)
    var santaTurn = true

    input.forEach { dirStr ->
        val dir = Direction.fromChar(dirStr)
        if (santaTurn) {
            visited.add(currPos)
            currPos += dir.unitVector
        } else {
            visited.add(roboPos)
            roboPos += dir.unitVector
        }

        santaTurn = !santaTurn
    }

    println("Visited ${visited.size} unique homes")
}
