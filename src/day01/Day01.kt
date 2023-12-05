package day01

import println
import readInput

fun main() {
    class A; val dir = A().javaClass.packageName

    fun part1(input: List<String>): Int {
        val a = input.map {
            val b = it
                .filter { it.isDigit() }
                .map { Character.getNumericValue(it) }
                .map { it.toString() }
            val c = b.first() + b.last()
            c.toInt()
        }

        a.println()
        a.sum().println()

        return a.sum()
    }

    fun part2(input: List<String>): Int {
        val replacements = listOf(
            "zero",
            "one",
            "two",
            "three",
            "four",
            "five",
            "six",
            "seven",
            "eight",
            "nine"
        )

        val a = input.map {inputLine ->
            var line = inputLine
            replacements
                .forEachIndexed { index, s ->
                    line = line.replace(s, "$s{$index}$s")
                }
            line
        }

        return part1(a)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("$dir/Day01_test")
    val testInput2 = readInput("$dir/Day01_test2")
    check(part1(testInput) == 142)
    check(part2(testInput2) == 281)

    val input = readInput("$dir/Day01")
    println("Part1 solution: ${part1(input)}")
    println("Part2 solution: ${part2(input)}")
}
