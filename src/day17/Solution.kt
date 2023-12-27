package day17

import Direction
import Point
import alsoPrintOnLines
import containsPoint
import plus
import readInput
import solution

fun main() {
    class A;
    val dir = A().javaClass.packageName

    data class Situation(val point: Point, val cost: Int, val recentStraights: Int, val from: Direction)

    fun part1(input: List<String>): Long {

        val start = Situation(Point(0, 0), 0, 0, from = Direction.WEST)

        val needsToVisit = mutableListOf(start)
        val visited = mutableMapOf<Point, Situation>()

        val debugPoint = Point(10,6)

        while (needsToVisit.isNotEmpty()) {
            val current = needsToVisit.minBy { it.cost }
            visited[current.point] = current

//            val history = visited[current.point + current.from.dir]
//            if (history != null) {
//                visited[current.point] = history + current
//            } else {
//                visited[current.point] = listOf(current)
//            }
            if (current.point == debugPoint) {
                println("1 ${current}")
            }
            needsToVisit.remove(current)
            Direction.entries.forEach { nextDirection ->


                val nextPoint = current.point + nextDirection.dir


                if (nextPoint.x == input.first().lastIndex && nextPoint.y == input.lastIndex) {
//                    println("found ${visited[current.point]}")

//                    history!!.alsoPrintOnLines()
//                    val goodPath = visited[current.point]!!.map { it.point }
//                    input.indices.forEach { row ->
//                        input.first().indices.forEach { col ->
//                            if (goodPath.contains(Point(col, row))) {
//                                print(input[row][col])
//                            } else {
//                                print(' ')
//                            }
//                        }
//                        println()
//                    }

                    return current.cost + input[current.point.y][current.point.x].digitToInt().toLong()
                }
                val recentStraights = if (current.from == nextDirection.opposite()) {
                    current.recentStraights + 1
                } else {
                    0
                }

                val history = visited[nextPoint]
                val isBetterThanVisited = history == null || history.cost > current.cost

                if ( isBetterThanVisited
                    && input.containsPoint(nextPoint)
                    && recentStraights < 3
                    && nextDirection != current.from
                ) {
                    val newCost = current.cost + input[current.point.y][current.point.x].digitToInt()
                    if (current.point == debugPoint) {
//                        if (history!!.last().cost == newCost) {
//                            println("2 $current")
//                            println("2 $history")
//
//                            println("2 $nextDirection")
//                            println("2 newCost $newCost")
//
//                        }
                    }

//                    if (visited.contains(nextPoint)) {
//                        if (visited[nextPoint]!!.last().cost > current.cost || visited[nextPoint]!!.last().recentStraights > current.recentStraights) {
//                            needsToVisit.add(
//                                Situation(
//                                    nextPoint,
//                                    newCost,
//                                    recentStraights,
//                                    nextDirection.opposite()
//                                )
//                            )
//                        }
//                    } else {
                        needsToVisit.add(
                            Situation(
                                nextPoint,
                                newCost,
                                recentStraights,
                                nextDirection.opposite()
                            )
                        )
//                    }
                }
            }
        }

        println("solutions: ${visited.size}")

//        visited.forEach { t, u ->
//            val goodPath = u.map { it.point }
//            input.indices.forEach { row ->
//                input.first().indices.forEach { col ->
//                    if (goodPath.contains(Point(col, row))) {
//                        print(input[row][col])
//                    } else {
//                        print(' ')
//                    }
//                }
//                println()
//            }
//
//            println()
//            println()
//            println()
//        }


//        val solution = visited[Point(input.first().lastIndex, input.lastIndex)]!!
//        solution.alsoPrintOnLines()
//        val goodPath = solution.map { it.point }
//
//        input.indices.forEach { row ->
//            input.first().indices.forEach { col ->
//                if (goodPath.contains(Point(col, row))) {
//                    print(input[row][col])
//                } else {
//                    print(' ')
//                }
//            }
//            println()
//        }
//        return solution.last().cost.toLong()
        return -1L
    }

    fun part2(input: List<String>): Long {
        return input.mapIndexed { index, line ->
            1L
        }.sum()
    }

    val testInput = readInput("$dir/test_input")

    solution("Part1 test: ", 102L, true) { part1(testInput) }
//    solution("Part2 test: ", 1L, true) { part2(testInput) }
    val input = readInput("$dir/input")
    solution("Part1 solution: ") { part1(input) }
//    solution("Part2 solution: ") { part2(input) }
}
