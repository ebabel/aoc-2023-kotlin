package day02

import readInput

fun main() {
    fun part1(input: List<String>): Int {
        val mapOfGames = input.map { line ->
            val a = line.split(":")
            val gameIndex = a.first().substringAfter("Game ").toInt()
            val sets = a.last().split(";")
            var possible = true
            sets.map { set ->
//                println("1: $set")
                set.trim().split(", ").forEach {
                    var greens = 0
                    var reds = 0
                    var blues = 0
//                    println("2: $set")
                    val num = it.substringBefore(" ").toInt()
//                    println("num $num")

                    if (it.contains("green")) {
                        greens += num
                    } else if (it.contains("red")) {
                        reds += num
                    } else if (it.contains("blue")){
                        blues += num
                    } else {
                        println("unexpected? $it")
                    }

                    // only 12 red cubes, 13 green cubes, and 14 blue cubes
                    if (possible && (reds > 12 || greens > 13 || blues > 14)) {
                        possible = false
                    }

                }
            }

            if (possible) {
                gameIndex
            } else {
//                println("game $gameIndex: greens $greens r $reds b $blues not possible :(")
                0
            }

        }
        return mapOfGames.sum()
    }

    fun part2(input: List<String>): Int {
        val mapOfGames = input.map { line ->
            val a = line.split(":")
            val sets = a.last().split(";")
            var possible = true
            var max_greens = 0
            var max_reds = 0
            var max_blues = 0
            sets.map { set ->
//                println("1: $set")
                set.trim().split(", ").forEach {
                    var greens = 0
                    var reds = 0
                    var blues = 0
//                    println("2: $set")
                    val num = it.substringBefore(" ").toInt()
//                    println("num $num")

                    if (it.contains("green")) {
                        greens += num
                    } else if (it.contains("red")) {
                        reds += num
                    } else if (it.contains("blue")){
                        blues += num
                    } else {
                        println("unexpected? $it")
                    }

                    // only 12 red cubes, 13 green cubes, and 14 blue cubes
                    if (possible && (reds > 12 || greens > 13 || blues > 14)) {
                        possible = false
                    }

                    max_blues = maxOf(max_blues, blues)
                    max_greens = maxOf(max_greens, greens)
                    max_reds = maxOf(max_reds, reds)
                }
            }

            val power = max_blues * max_greens * max_reds

            power

        }
        return mapOfGames.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
//    val testInput2 = readInput("Day02_test2")
    check(part1(testInput) == 8)
    check(part2(testInput) == 2286)

    val input = readInput("Day02")
    println("Part1 solution: ${part1(input)}")
    println("Part2 solution: ${part2(input)}")
}
