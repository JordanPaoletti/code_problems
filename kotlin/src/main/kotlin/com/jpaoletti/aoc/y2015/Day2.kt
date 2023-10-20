package com.jpaoletti.aoc.y2015

import com.jpaoletti.aoc.inputLines
import kotlin.math.*

data class Box(
    val length: Int,
    val width: Int,
    val height: Int
) {
    companion object {
        fun fromStr(str: String): Box {
            val split = str.trim().split("x")
            return Box(
                length = split[0].toInt(),
                width = split[1].toInt(),
                height = split[2].toInt()
            )
        }
    }
}

data class BoxCosts(
    val paperLength: Long,
    val ribbonLength: Long
) {
    fun combine(o: BoxCosts): BoxCosts {
        return BoxCosts(
            paperLength = this.paperLength + o.paperLength,
            ribbonLength = this.ribbonLength + o.ribbonLength
        )
    }
}

fun main() {
    println("Day 2:")
    println()
//    pt1()
//    println()
    pt2()
    println()
}

private fun pt1() {
    println("Part 1:")
    val input = inputLines(year = 2015, day = 2, part = 1)
    val totalPaperLength = input.asSequence().map(Box.Companion::fromStr).map(::neededPaper).sum()
    println("Total Paper Length: $totalPaperLength")
}

private fun neededPaper(box: Box): Long {
    var minSide = Int.MAX_VALUE
    var paperLength = 0

    var side = box.length * box.width
    minSide = min(minSide, side)
    paperLength += side * 2

    side = box.height * box.width
    minSide = min(minSide, side)
    paperLength += side * 2

    side = box.height * box.length
    minSide = min(minSide, side)
    paperLength += side * 2

    return paperLength.toLong() + minSide
}

private fun pt2() {
    println("Part 2:")
    val input = inputLines(year = 2015, day = 2, part = 1) // same as pt1
    val boxCosts = input.asSequence().map(Box.Companion::fromStr).map(::boxCosts).reduce { b1, b2 -> b1.combine(b2) }

    println("Total Paper Length: ${boxCosts.paperLength}")
    println("Total Ribbon Length: ${boxCosts.ribbonLength}")
}

private fun boxCosts(box: Box): BoxCosts {
    return BoxCosts(
        paperLength = neededPaper(box),
        ribbonLength = neededRibbon(box)
    )
}

private fun neededRibbon(box: Box): Long {
    val dims = listOf(box.height, box.width, box.length).sorted()
    val bowLength = dims.fold(1L) { acc, i -> acc * i }
    val wrapLength = 2L * dims[0] + 2L * dims[1]

    return bowLength + wrapLength
}
