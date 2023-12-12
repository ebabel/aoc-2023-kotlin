package day09

import alsoPrintOnLines
import getInts
import readInput
import solution

fun main() {
    class A;
    val dir = A().javaClass.packageName

    fun part1(input: List<String>): Long {
        return input.mapIndexed { index, line ->
            var a = 1
            var b =
                line.getInts().windowed(2, 1).map { it.last() - it.first() }.toList().alsoPrintOnLines()
            val c = mutableListOf(line.getInts().last())
            c+= b.last()
            while (a != 0) {
                b =
                    b.windowed(2, 1).map { it.last() - it.first() }.toList().alsoPrintOnLines()
                c += b.last()
                a = b.sum()
            }
            println("c $c")
            c.sum().toLong()
        }.sum()
    }

    fun part2(input: List<String>): Long {
        return input.mapIndexed { index, line ->
            var a = 1
            var b =
                line.getInts().windowed(2, 1).map { it.last() - it.first() }.toList().alsoPrintOnLines()
            val bs = mutableListOf(b)
            val c = mutableListOf(line.getInts().first())
            c+= b.first()
            while (a != 0) {
                b =
                    b.windowed(2, 1).map { it.last() - it.first() }.toList().alsoPrintOnLines()
                bs.add(b)
                c += b.first()
                a = b.sum()
            }

            var prevB = 0
            var z = 0
            bs.reversed().forEach {  i ->
                prevB = i.first() - prevB
                println("prevB $prevB")
            }
            prevB = line.getInts().first() - prevB
            println("c $c")
                println("prevB $prevB")
            prevB.toLong()
        }.sum()
    }

    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 114L, true) { part1(testInput) }
    solution("Part2 test: ", 2L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ") { part1(input) }
    solution("Part2 solution: ") { part2(input) }
}
