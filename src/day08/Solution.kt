package day08

import alsoPrintOnLines
import println
import readInput
import solution
import takeAfter

fun main() {
    class A;
    val dir = A().javaClass.packageName

    data class RL(val left: String, val right: String)
    class Node(val left: Node, val right: Node)

    fun part1(input: List<String>): Long {
        input.first().length.println()
        val ex1 = "AAA = (".length

        val map = input.takeAfter(2).mapIndexed { index, line ->
            val left = line.substringAfter("(").take(3)
            val right = line.takeLast(4).take(3)
            line.take(3) to RL(left, right)
        }.toMap()



        map.toList().alsoPrintOnLines()

        var found = false
        var steps = 0
        var current: RL? = map["AAA"]!!
        while (!found) {
            input.first().forEach {
                when (it) {
                    'R' -> {
//                        println("current!!.right ${current!!.right}")
                        current = map[current!!.right]
                    }

                    'L' -> {
//                        println("current!!.left ${current!!.left}")
                        current = map[current!!.left]
                    }
                    else -> error("")
                }
                if (current!!.right == "ZZZ" && current!!.left == "ZZZ") {
                    found = true
                }
                steps++
            }
            println("repeating ${current!!.left}")

        }


        return steps.toLong()
    }

    fun part2(input: List<String>): Long {
        return input.mapIndexed { index, line ->
            1L
        }.sum()
    }

    val testInput = readInput("$dir/test_input")

    solution("Part1 test: ", 6L, true) { part1(testInput) }
//    solution("Part2 test: ", 1L, true) { part2(testInput) }
    val input = readInput("$dir/input")
    solution("Part1 solution: ") { part1(input) }
//    solution("Part2 solution: ") { part2(input) }
}
