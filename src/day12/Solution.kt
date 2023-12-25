package day12

import alsoPrintOnLines
import getIntList
import getInts
import println
import readInput
import solution
import kotlin.collections.ArrayList

fun main() {
    class A;
    val dir = A().javaClass.packageName

    fun part1(input: List<String>): Long {
        return input.mapIndexed { index, line ->
            val ints = line.getIntList()
//            println(line.substringBefore(" ").split(".").filter { it.isNotEmpty() })
//            println(line.substringBefore(" ").split("#").filter { it.isNotEmpty() })

            val puzz = line.substringBefore(" ")
            val needed = ints.sum() + ints.size
            if (needed == puzz.length) {
                println("only 1 poss")
            }
            val possibilities = mutableListOf<String>("")
            puzz.forEachIndexed { index, c ->
                val localPoss = possibilities.toMutableList()
                possibilities.clear()
                if (c != '?') {
                    possibilities.addAll(localPoss.map { it + c })
                } else {
                    possibilities.addAll(localPoss.map { it + '.' })
                    possibilities.addAll(localPoss.map { it + '#' })

                }
            }

            possibilities.count {
                it.split(".").filter { it.isNotEmpty() }.map { it.length } == ints
            }.toLong()

//            println( "$index ${possibilities.count()}")
//            1L
        }.sum()
    }

    fun part2(input: List<String>): Long {
        return input.mapIndexed { index, line ->
            val ints = line.getIntList() + line.getIntList() + line.getIntList() + line.getIntList() + line.getIntList()
//            println(line.substringBefore(" ").split(".").filter { it.isNotEmpty() })
//            println(line.substringBefore(" ").split("#").filter { it.isNotEmpty() })

            val puzz1 = line.substringBefore(" ")
            val puzz = "$puzz1?$puzz1?$puzz1?$puzz1?$puzz1"
            val needed = ints.sum() + ints.size
            if (needed == puzz.length) {
                println("only 1 poss")
            }

            var workingStart = 0

            while (workingStart < puzz.length) {

                if (puzz[workingStart] == '.') {
                    workingStart++
                } else {

                }
            }


//            ints.forEach { workingInt ->
//
//                val foundSpaceBigEnough = false
//                while (foundSpaceBigEnough) {
//                    val firstDot = puzz.indexOf('.', startIndex = workingStart)
//                    if (firstDot <= workingInt) {
//                        workingStart = workingInt + 1 // one after as delim
//                        foundSpaceBigEnough
//                    } else {
//                        workingStart = firstDot + 1
//                    }
//                }
//            }
//            puzz.substringBefore(".")

//            println( "$index ${possibilities.count()}")
            1L
        }.sum()
    }

    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 21L, true) { part1(testInput) }
    solution("Part2 test: ", 525152L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ") { part1(input) }
//    solution("Part2 solution: ") { part2(input) }
}
