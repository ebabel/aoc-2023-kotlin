package day0X

import readInput
import solution

fun main() {
    class A; val dir = A().javaClass.packageName

    fun part1(input: List<String>): Long {
        return input.mapIndexed { index, line ->
            1L

        }.sum()
    }

    fun part2(input: List<String>): Long {
        return input.mapIndexed { index, line ->
            1L
        }.sum()
    }
    val testInput = readInput("$dir/test_input")

    solution("Part1 test: ", 1L, true) { part1(testInput) }
//    solution("Part2 test: ", 1L, true) { part2(testInput) }
    val input = readInput("$dir/input")
    solution("Part1 solution: ") { part1(input) }
//    solution("Part2 solution: ") { part2(input) }
}
