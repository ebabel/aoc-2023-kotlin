package day06

import getLongList
import productOf
import readInput
import solution

fun main() {
    class A; val dir = A().javaClass.packageName

    fun part1(input: List<String>): Long {
        val times = input.first().getLongList()
        val distances = input.last().getLongList()
        return times.mapIndexed { index, t ->
            var wins = 0L
            (1..<t).forEach {t2 ->
                val didWin = t2 * (t - t2) > distances[index]
//                println("$t2 beats? ${distances[index]}   $didWin")
                if (didWin) {
                    wins++
                }
            }
            wins

        }.productOf()

    }

    fun part2(input: List<String>): Long {
        val time = input.first().substringAfter(" ").replace(" ", "").toLong()
        val distance = input.last().substringAfter(" ").replace(" ", "").toLong()
        return time.let {  t ->
            var wins = 0L
            (1..<t).forEach {t2 ->
                val didWin = t2 * (t - t2) > distance
//                println("$t2 beats? ${distances[index]}   $didWin")
                if (didWin) {
                    wins++
                }
            }
            wins

        }
    }
    val testInput = readInput("$dir/test_input")

    solution("Part1 test: ", 288L, true) { part1(testInput) }
    solution("Part2 test: ", 71503L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ") { part1(input) }
    solution("Part2 solution: ") { part2(input) }
}
