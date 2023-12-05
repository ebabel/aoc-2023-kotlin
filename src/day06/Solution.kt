package day06

import expecting
import readInput

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

    check(part1(testInput).expecting(13L))
//    check(part2(testInput).expecting(30L))

    val input = readInput("$dir/input")
    println("Part1 solution: ${part1(input)}")
//    println("Part2 solution: ${part2(input)}")
}
