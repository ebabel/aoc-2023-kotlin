package day18

import Direction
import Point
import PointL
import alsoPrintOnLines
import getInts
import manhattanDistance
import plus
import println
import readInput
import solution
import times
import java.lang.Integer.parseInt

fun main() {
    class A; val dir = A().javaClass.packageName

    fun part1(input: List<String>): Long {

        var current = Point(0,0)
        var lava = 0
        val b = input.mapIndexed { index, line ->
            val dir = when (line.take(1)) {
                "U" -> Direction.NORTH
                "D" -> Direction.SOUTH
                "R" -> Direction.EAST
                "L" -> Direction.WEST
                else -> error("unknown ")
            }

            val amount = line.getInts().first()

            current += (dir.dir.times(amount))
            current
//            println(current)
        }


        val minY = b.minBy { it.y }.y
        val ySize = b.maxBy { it.y }.y - minY
        val minX = b.minBy { it.x }.x
        val xSize = b.maxBy { it.x }.x - minX


        val a = MutableList(ySize+1){
            MutableList(xSize+1){
                ' '
            }
        }

        a.alsoPrintOnLines()
        println("minX $minX")
        println("minY $minY")
        println("ySize $ySize")
        println("xSize $xSize")

        input.mapIndexed { index, line ->
            val dir = when (line.take(1)) {
                "U" -> Direction.NORTH
                "D" -> Direction.SOUTH
                "R" -> Direction.EAST
                "L" -> Direction.WEST
                else -> error("unknown ")
            }

            val amount = line.getInts().first()

            (0..<amount).forEach {
//                println("${current.y-minY}z${current.x-minX}")
                a[current.y-minY][current.x-minX] = '#'
                current += dir.dir
                lava++
            }

//            println(current)
        }
        a.forEach {

            it.joinToString("").println()
        }

        println("lava so far $lava")

        current = Point(1 - minX, 1 - minY)
        lava++

        val nodesToVisit = mutableListOf(current)
        while (nodesToVisit.isNotEmpty()) {
            val first = nodesToVisit.removeFirst()

//            a[first.y][first.x] = 'o'
            lava++
            Direction.entries.forEach {
                val next = first + it.dir
                if (a[next.y][next.x] == ' ') {
                    a[next.y][next.x] = 'o'
                    nodesToVisit.add(next)
                }
            }
//            (-20..20).forEach {
//                a[it-minY].joinToString("").println()
//            }

//            println("${ nodesToVisit.size } $lava ${Direction.entries}")
        }
        println()
        println()

//        a.forEach {
//
//            it.joinToString("").println()
//        }

        return a.sumOf {
            it.joinToString("").replace(" ","").length
        }.toLong()
    }

    fun part2(input: List<String>): Long {

        var current = Point(0,0)
        var perimeter = 0L
        var lava = 0
        val b = input.mapIndexed { index, line ->
            val dir = when (line.takeLast(2).first()) {
                '3' -> Direction.NORTH
                '1' -> Direction.SOUTH
                '0' -> Direction.EAST
                '2' -> Direction.WEST
                else -> error("unknown ")
            }

            val amount = parseInt(line.substringAfter("#").take(5),16)

            current += (dir.dir.times(amount))
            perimeter += amount
            current
//            println(current)
        }

        b.map { PointL(it.x.toLong(), it.y.toLong()) }.also {

            val mutableList = it.toMutableList()
            mutableList.add(0,PointL(0L,0L))

            return getSurfaceArea(mutableList.toList().alsoPrintOnLines()) + perimeter
        }

    }
    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 62L, true) { part1(testInput) }
    solution("Part2 test: ", 952408144115L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ") { part1(input) }
    solution("Part2 solution: ") { part2(input) }

}


private fun getTotalManhattanDistance(corners: List<PointL>): Long {
    var total: Long = 0

    for (i in corners.indices) {
        val c1: PointL = corners[i]
        val c2: PointL = corners[if (i == corners.size - 1) 0 else i + 1]
        total += c1.manhattanDistance(c2)
//        total += abs(c1.x - c2.x) + abs(c1.y - c2.y)
    }

    return total
}
// https://github.com/ash42/adventofcode/blob/main/adventofcode2023/src/nl/michielgraat/adventofcode2023/day18/Day18.java
private fun getSurfaceArea(corners: List<PointL>): Long {
    // Start with shoelace
    var area: Long = 0
    for (i in corners.indices) {
        val c1: PointL = corners[i]
        val c2: PointL = corners[if (i == corners.size - 1) 0 else i + 1]
        val factor: Long = c1.x * c2.y - c1.y * c2.x
        area += factor
    }
    area /= 2
    // Now apply Pick's theorem
    return area - (getTotalManhattanDistance(corners) / 2) + 1
}