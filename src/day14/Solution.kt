package day14

import println
import readInput
import solution

fun main() {
    class A; val dir = A().javaClass.packageName

    fun part1(input: List<String>): Long {
        val a = ArrayList<MutableList<Char>>()
        input.forEachIndexed { rowIndex, s ->
            s.forEachIndexed { cIndex, c ->
                if (a.size-1 != rowIndex) {
                    a.add(List(s.length) { '.' }.toMutableList())
//                    println("adding row $a")
                }
                if (c == 'O') {
                    var i = rowIndex
                    while (i > 0 && a[i - 1][cIndex] == '.') {
                        i--
                    }
                    a[i][cIndex] = 'O'
                } else {
                    a[rowIndex][cIndex] = c
                }
            }
        }

        return a.mapIndexed { rowIndex, chars ->
            chars.mapIndexed { index, c ->
//                println("$c ${c == 'O'}")
                if (c == 'O') {
                    (input.size - rowIndex).println()
                } else {
                    0
                }
            }.sum()
        }.sum().toLong()


    }

    fun part2(input: List<String>): Long {
        return input.mapIndexed { index, line ->
            1L
        }.sum()
    }
    val testInput = readInput("$dir/test_input")

    solution("Part1 test: ", 136L, true) { part1(testInput) }
//    solution("Part2 test: ", 1L, true) { part2(testInput) }
    val input = readInput("$dir/input")
    solution("Part1 solution: ") { part1(input) }
//    solution("Part2 solution: ") { part2(input) }
}
