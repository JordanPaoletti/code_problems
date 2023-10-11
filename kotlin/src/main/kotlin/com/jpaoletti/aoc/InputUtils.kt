package com.jpaoletti.aoc

import java.io.File

fun getCwd(): String {
    return System.getProperty("user.dir")
}

fun inputLines(year: Int, day: Int, part: Int): List<String> {
    val filename = "${getCwd()}/input/aoc/y${year}/day${day}/pt${part}"
    val file = File(filename)
    return file.readLines()
}