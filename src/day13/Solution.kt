package day13

import alsoPrintOnLines
import println
import readInput
import solution

fun main() {
    class A;
    val dir = A().javaClass.packageName

    fun part1(input: List<String>): Long {
        val puzzles: MutableList<MutableList<String>> = emptyList<MutableList<String>>().toMutableList()

        val workingList = mutableListOf<String>()
        input.mapIndexed { index, line ->

            if (line.isNotEmpty()) {
                workingList.add(line)
            } else {
                println("store")
                puzzles.add(workingList.toMutableList())
                workingList.clear()
            }
        }
        puzzles.add(workingList.toMutableList())
        workingList.clear()

        val pivotPuzzles = puzzles.map { puzzle ->
            puzzle.first().indices.map { index ->
                puzzle.map { it[index] }.joinToString("")
            }
        }
        var foundSolutions = 0

        pivotPuzzles.forEach {pivotPuzzle  ->
            pivotPuzzle.forEachIndexed { index, s ->

                if (index > 0 && s == pivotPuzzle[index - 1]) {
                    var i = 0
                    var centerFound = true
                    println("cindex - i - 1 ${index - i - 1}")
                    println("cindex + i ${index + i}")
                    println("cpuzzle.lastIndex ${pivotPuzzle.lastIndex}")
                    while (centerFound && index - i - 1 >= 0 && pivotPuzzle.lastIndex >= index + i) {
                        println("ctest ${pivotPuzzle[index - i - 1]} vs ${pivotPuzzle[index + i]}")
                        if (pivotPuzzle[index - i - 1] != pivotPuzzle[index + i]) {
                            println("cbroken")
                            centerFound = false
                            break
                        }
                        i++
                    }

                    if (centerFound) {
                        foundSolutions += index
                        println("center found between column ${index} and ${index + 1} ${pivotPuzzle[index]}")
                    }

                }
            }
        }

        puzzles.forEach { puzzle ->
            var notFound = true

            println("puzzle $puzzle")
            puzzle.forEachIndexed { index, s ->

                if (index > 0 && s == puzzle[index - 1]) {
                    var i = 0
                    var centerFound = true
                    println("index - i - 1 ${index - i - 1}")
                    println("index + i ${index + i}")
                    println("puzzle.lastIndex ${puzzle.lastIndex}")
                    while (centerFound && index - i - 1 >= 0 && puzzle.lastIndex >= index + i) {
                        println("test ${puzzle[index - i - 1]} vs ${puzzle[index + i]}")
                        if (puzzle[index - i - 1] != puzzle[index + i]) {
                            println("broken")
                            centerFound = false
                            break
                        }
                        i++
                    }

                    if (centerFound) {
                        foundSolutions += index * 100
                        println("center found between row ${index} and ${index + 1} ${puzzle[index]}")
                    }

                }
            }
        }
        return foundSolutions.toLong()
    }

    fun part2(input: List<String>): Long {
        return input.mapIndexed { index, line ->
            1L
        }.sum()
    }

    val testInput = readInput("$dir/test_input")

    solution("Part1 test: ", 405L, true) { part1(testInput) }
//    solution("Part2 test: ", 1L, true) { part2(testInput) }
    val input = readInput("$dir/input")
    solution("Part1 solution: ") { part1(input) }
//    solution("Part2 solution: ") { part2(input) }
}
