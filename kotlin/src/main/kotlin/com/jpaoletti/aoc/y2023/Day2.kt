package com.jpaoletti.aoc

// https://adventofcode.com/2023
fun main() {
    println("Day 2:")
    println()

    pt1()
    println()

    pt2()
    println()
}

data class PulledSet(val red: Int, val green: Int, val blue: Int)
data class Game(val id: Int, val sets: List<PulledSet>)

val gameRegex = """Game (\d+):(.*)$""".toRegex()
val redRegex = """.* (\d+) red.*""".toRegex()
val greenRegex = """.* (\d+) green.*""".toRegex()
val blueRegex = """.* (\d+) blue.*""".toRegex()

private fun parseGame(line: String): Game {
    val match = gameRegex.matchEntire(line)!!
    val id = match.groups[1]!!.value.toInt()
    val pulls = match.groups[2]!!.value
        .split(";")
        .map { str ->
            PulledSet(
                red  = parsePull(redRegex, str),
                green = parsePull(greenRegex, str),
                blue = parsePull(blueRegex, str)
            )
        }

    return Game(id, pulls)
}

private fun parsePull(regex: Regex, pull: String): Int {
    val match = regex.find(pull)
    return if (match == null) 0 else match.groupValues[1].toInt()
}

private fun gameIsValid(game: Game, total: PulledSet): Boolean {
    game.sets.forEach {
        when {
            it.red > total.red -> return false
            it.green > total.green -> return false
            it.blue > total.blue -> return false
        }
    }

    return true
}

private fun pt1() {
    println("Part 1:")
    val input = inputLines(year = 2023, day = 2, part = 1)
//    val input = inputLinesEx(year = 2023, day = 2)
    val total = PulledSet(red = 12, green = 13, blue = 14)
    val valid = input.asSequence()
        .map(::parseGame)
        .filter { gameIsValid(it, total) }
        .map(Game::id)
        .toList()

    val sum = valid.sum()
    println("sum $sum")
}

private fun minPowerSet(game: Game): Long {
    val minRed = game.sets.maxOfOrNull(PulledSet::red)!!
    val minGreen = game.sets.maxOfOrNull(PulledSet::green)!!
    val minBlue = game.sets.maxOfOrNull(PulledSet::blue)!!

    var product = 1L
    if (minRed > 0) {
        product *= minRed
    }
    if (minGreen > 0) {
        product *= minGreen
    }
    if (minBlue > 0) {
        product *= minBlue
    }

    return product
}

private fun pt2() {
    println("Part 2:")
    val input = inputLines(year = 2023, day = 2, part = 1)
//    val input = inputLinesEx(year = 2023, day = 2)
    val sum = input.asSequence()
        .map(::parseGame)
        .map(::minPowerSet)
        .sum()

    println("sum $sum")
}

