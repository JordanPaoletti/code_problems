package com.jpaoletti

import java.io.File

fun pwd(): String {
    return System.getProperty("user.dir")
}

fun getLinesFromFile(filename: String): List<String> {
    val lines = mutableListOf<String>()
    val inputStream = File(filename).inputStream()
    inputStream.bufferedReader().useLines { lines.addAll(it) }
    return lines
}