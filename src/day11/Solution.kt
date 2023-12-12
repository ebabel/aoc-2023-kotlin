package day11

import PointL
import manhattanDistance
import readInput
import solution

fun main() {
    class A;
    val dir = A().javaClass.packageName

    fun part1(input: List<String>): Long {

        val emptyColumns = (0..<input.first().length).mapNotNull { x ->
            if (!input.map { it[x] }.contains('#')) {
                x
            } else {
                null
            }
        }
        val emptyRows = input.mapIndexedNotNull { index, s ->
            if (!s.contains('#')) {
                index
            } else {
                null
            }
        }
        println("empty cols: $emptyColumns rows: $emptyRows")

        val list = mutableListOf<PointL>()
        var y = 0
        var y1 = 0L
        while (y < input.size) {
            if (!emptyRows.contains(y)) {

                var x = 0
                var x1 = 0L
                while (x < input.first().length) {

                    if (!emptyColumns.contains(x)) {
                        if (input[y][x] == '#') {
                            list.add(PointL(x1, y1))
                        }
                    } else {
                        x1++
                    }

                    x++
                    x1++
                }
            } else {
                y1++
            }
            y++
            y1++
        }

        return (0..<list.size - 1).map { a ->
            (a..<list.size).map { b ->
                list[a].manhattanDistance(list[b])
            }
        }.flatten().sum()

    }

    fun part2(input: List<String>, modifier: Long): Long {


        val emptyColumns = (0..<input.first().length).mapNotNull { x ->
            if (!input.map { it[x] }.contains('#')) {
                x
            } else {
                null
            }
        }
        val emptyRows = input.mapIndexedNotNull { index, s ->
            if (!s.contains('#')) {
                index
            } else {
                null
            }
        }

        val list = mutableListOf<PointL>()
        var y = 0
        var y1 = 0L
        val galaxyZoomMod = modifier - 1L
        while (y < input.size) {
            if (!emptyRows.contains(y)) {

                var x = 0
                var x1 = 0L
                while (x < input.first().length) {

                    if (!emptyColumns.contains(x)) {
                        if (input[y][x] == '#') {
                            list.add(PointL(x1, y1))
                        }
                    } else {
                        x1 += galaxyZoomMod
                    }

                    x++
                    x1++
                }
            } else {
                y1 += galaxyZoomMod
            }
            y++
            y1++
        }

        return (0..<list.size - 1).map { a ->
            (a..<list.size).map { b ->
                list[a].manhattanDistance(list[b])
            }
        }.flatten().sum()
    }

    val testInput = readInput("$dir/test_input")

    solution("Part1 test: ", 374L, true) { part1(testInput) }
    solution("Part2 test: ", 1030L, true) { part2(testInput, 10) }
    val input = readInput("$dir/input")
    solution("Part1 solution: ", 9742154L, true) { part1(input) }
    solution("Part2 solution: ", 411142919886L, true) { part2(input, 1000000) }
}
