package day16

import Direction
import Direction.*
import Point
import plus
import readInput
import solution

fun main() {
    class A;
    val dir = A().javaClass.packageName

    data class Situation(val point: Point, val direction: Direction)


    fun solveMaze(start: Situation, input: List<String>): Long {
        val needsProcessing = mutableListOf(start)
        val visited = needsProcessing.toMutableList()
        visited.clear()

        while (needsProcessing.isNotEmpty()) {
            val current = needsProcessing.removeFirst()
            visited.add(current)

            val currentDir = current.direction
            val currentPoint = current.point
            val currentChar = input[currentPoint.y][currentPoint.x]

            val newDirections: List<Direction> = when (currentChar) {
                '.' -> {
                    //                currentPoint += currentDir.dir
                    listOf(currentDir)
                }

                '-' -> {
                    when (currentDir) {
                        NORTH, SOUTH -> listOf(WEST, EAST)
                        WEST, EAST -> listOf(currentDir)
                    }
                }

                '|' -> {
                    when (currentDir) {
                        NORTH, SOUTH -> listOf(currentDir)
                        WEST, EAST -> listOf(NORTH, SOUTH)
                    }
                }

                '/' -> {
                    when (currentDir) {
                        NORTH -> listOf(EAST)
                        SOUTH -> listOf(WEST)
                        WEST -> listOf(SOUTH)
                        EAST -> listOf(NORTH)
                    }
                }

                '\\' -> {
                    when (currentDir) {
                        NORTH -> listOf(WEST)
                        SOUTH -> listOf(EAST)
                        WEST -> listOf(NORTH)
                        EAST -> listOf(SOUTH)
                    }
                }

                else -> error("unknown $currentChar ")
            }

            newDirections.forEach {
                val newSituation = Situation(currentPoint + it.dir, it)
                if (input.first().indices.contains(newSituation.point.x) &&
                    input.indices.contains(newSituation.point.y) &&
                    !visited.contains(newSituation)
                ) {
                    needsProcessing.add(newSituation)
//                    println("add $newSituation")
                }
            }
        }

        return visited.map { it.point }.toSet().count().toLong()
    }

    fun part1(input: List<String>): Long {

        val start = Situation(Point(0, 0), EAST)
        return solveMaze(start, input)
    }

    fun part2(input: List<String>): Long {

        println("top")
        val top = input.first().indices.map {
            solveMaze(Situation(Point(it, 0), SOUTH), input)
        }.max()

        println("bottom")
        val bottom = input.first().indices.map {
            solveMaze(Situation(Point(it, input.indices.last), NORTH), input)
        }.max()

        println("left")
        val left = input.indices.map {
            solveMaze(Situation(Point(0, it), EAST), input)
        }.max()

        println("right")
        val right = input.indices.map {
            solveMaze(Situation(Point(input.first().indices.last, it), WEST), input)
        }.max()

        return listOf(top, bottom, left, right).max()

    }

    val testInput = readInput("$dir/test_input")

    solution("Part1 test: ", 46L, true) { part1(testInput) }
    solution("Part2 test: ", 51L, true) { part2(testInput) }
    val input = readInput("$dir/input")
    solution("Part1 solution: ", 7307, true) { part1(input) }
    solution("Part2 solution: ", 7635, true) { part2(input) } // 33s
}
