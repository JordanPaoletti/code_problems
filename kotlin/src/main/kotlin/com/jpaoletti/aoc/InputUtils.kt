package com.jpaoletti.aoc

import java.io.File

fun getCwd(): String {
    return System.getProperty("user.dir")
}

fun inputLines(year: Int, day: Int, part: Int): List<String> {
    return inputLines(year, day, "pt$part")
}

fun inputLinesEx(year: Int, day: Int): List<String> {
    return inputLines(year, day, "ex")
}

fun inputLines(year: Int, day: Int, file: String): List<String> {
    val filename = "${getCwd()}/kotlin/input/aoc/y${year}/day${day}/${file}"
    val file = File(filename)
    return file.readLines()
}
