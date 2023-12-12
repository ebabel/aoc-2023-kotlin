package day10

import Point
import alsoPrintOnLines
import plus
import readInput
import solution

enum class Dir(val mod: Point) {
    North(Point(0, -1)),
    South(Point(0, 1)),
    East(Point(1, 0)),
    West(Point(-1, 0));
//    Start(Point(1, 0));

    fun opposite() = when (this) {
        North -> South
        South -> North
        East -> West
        West -> East
        else -> error("")
    }
}

fun main() {
    class A;
    val dir = A().javaClass.packageName

    // if I look east of current position L connects to me
    val dirToChecks = mapOf(
        Dir.North to listOf('|', 'F', '7'),
        Dir.South to listOf('|', 'J', 'L'),
        Dir.East to listOf('-', 'J', '7'),
        Dir.West to listOf('-', 'F', 'L'),
    )

    data class Track(val from: Dir, val current: Point, val index: Int)


    fun nextInLoop(input: List<String>, track: Track): Track {
        val from = track.from.mod
        val current = track.current
        val (newFrom, newCurrent) = when {
            from == Dir.North.mod && input[current.y][current.x] == '|' -> {
                Dir.North to Point(current.x, current.y + 1)
            }

            from == Dir.North.mod && input[current.y][current.x] == 'J' -> {
                Dir.East to Point(current.x - 1, current.y)
            }

            from == Dir.North.mod && input[current.y][current.x] == 'L' -> {
                Dir.West to Point(current.x + 1, current.y)
            }

            from == Dir.South.mod && input[current.y][current.x] == '|' -> {
                Dir.South to Point(current.x, current.y - 1)
            }

            from == Dir.South.mod && input[current.y][current.x] == '7' -> {
                Dir.East to Point(current.x - 1, current.y)
            }

            from == Dir.South.mod && input[current.y][current.x] == 'F' -> {
                Dir.West to Point(current.x + 1, current.y)
            }

            from == Dir.West.mod && input[current.y][current.x] == '-' -> {
                Dir.West to Point(current.x + 1, current.y)
            }

            from == Dir.West.mod && input[current.y][current.x] == '7' -> {
                Dir.North to Point(current.x, current.y + 1)
            }

            from == Dir.West.mod && input[current.y][current.x] == 'J' -> {
                Dir.South to Point(current.x, current.y - 1)
            }

            from == Dir.East.mod && input[current.y][current.x] == '-' -> {
                Dir.East to Point(current.x - 1, current.y)
            }

            from == Dir.East.mod && input[current.y][current.x] == 'F' -> {
                Dir.North to Point(current.x, current.y + 1)
            }

            from == Dir.East.mod && input[current.y][current.x] == 'L' -> {
                Dir.South to Point(current.x, current.y - 1)
            }

            else -> error("Illegal $from ${input[current.y][current.x]}")
        }
        return Track(from = newFrom, newCurrent, track.index + 1)
    }

//    fun part1(input: List<String>): Long {
//
//        var startTemp: Point? = null
//        input.forEachIndexed { index, s ->
//            val x = s.indexOf('S')
//            if (x != -1) {
//                startTemp = Point(x, index)
//                return@forEachIndexed
//            }
//        }
//        val start = startTemp!!
//        println("start: $start")
//
//        val nextInLoop = mutableListOf<Track>()
//        if (start.y > 0) {
//            val north = input[start.y - 1][start.x]
//
//            val dir1 = Dir.North
//            when (north) {
//                '|', 'F', '7' -> {
//
//                    println("added nort")
//                    nextInLoop.add(Track(Dir.South, start + dir1.mod, 1))
//                }
//            }
//        }
//        if (start.x > 0) {
//            val dir1 = Dir.West
//            val pointAtDir = start + dir1.mod
//            val charAtDir = input[pointAtDir.y][pointAtDir.x]
//            if (dirToChecks[dir1]!!.contains(charAtDir)) {
//                println("added west")
//                nextInLoop.add(Track(Dir.East, pointAtDir, 1))
//            }
//        }
//        if (start.x != input.first().indices.last) {
//            val dir1 = Dir.East
//            val pointAtDir = start + dir1.mod
//            val charAtDir = input[pointAtDir.y][pointAtDir.x]
//            if (dirToChecks[dir1]!!.contains(charAtDir)) {
//                println("added east $start $pointAtDir")
//                nextInLoop.add(Track(Dir.West, pointAtDir, 1))
//            }
//        }
//        if (start.y != input.indices.last) {
//            val dir1 = Dir.South
//            val pointAtDir = start + dir1.mod
//            val charAtDir = input[pointAtDir.y][pointAtDir.x]
//            if (dirToChecks[dir1]!!.contains(charAtDir)) {
//                println("added south")
//                nextInLoop.add(Track(Dir.North, pointAtDir, 1))
//            }
//        }
//        println("nextInLoop")
//        nextInLoop.alsoPrintOnLines()
//
//        while (nextInLoop.first().current != nextInLoop.last().current) {
//            nextInLoop.add(nextInLoop(input, nextInLoop.removeAt(0)))
//            nextInLoop.add(nextInLoop(input, nextInLoop.removeAt(0)))
//            println("nextInLoop")
//            nextInLoop.alsoPrintOnLines()
//        }
//
//        return nextInLoop.first().index.toLong()
//    }

    fun part2(input: List<String>): Long {

        var startTemp: Point? = null
        input.forEachIndexed { index, s ->
            val x = s.indexOf('S')
            if (x != -1) {
                startTemp = Point(x, index)
                return@forEachIndexed
            }
        }
        val start = startTemp!!
        println("start: $start")

        val nextInLoop = mutableListOf<Track>()
        if (start.y > 0) {
            val north = input[start.y - 1][start.x]

            val dir1 = Dir.North
            when (north) {
                '|', 'F', '7' -> {
//                    println("added nort")
                    nextInLoop.add(Track(Dir.South, start + dir1.mod, 1))
                }
            }
        }
        if (start.x > 0) {
            val dir1 = Dir.West
            val pointAtDir = start + dir1.mod
            val charAtDir = input[pointAtDir.y][pointAtDir.x]
            if (dirToChecks[dir1]!!.contains(charAtDir)) {
//                println("added west")
                nextInLoop.add(Track(Dir.East, pointAtDir, 1))
            }
        }
        if (start.x != input.first().indices.last) {
            val dir1 = Dir.East
            val pointAtDir = start + dir1.mod
            val charAtDir = input[pointAtDir.y][pointAtDir.x]
            if (dirToChecks[dir1]!!.contains(charAtDir)) {
//                println("added east $start $pointAtDir")
                nextInLoop.add(Track(Dir.West, pointAtDir, 1))
            }
        }
        if (start.y != input.indices.last) {
            val dir1 = Dir.South
            val pointAtDir = start + dir1.mod
            val charAtDir = input[pointAtDir.y][pointAtDir.x]
            if (dirToChecks[dir1]!!.contains(charAtDir)) {
//                println("added south")
                nextInLoop.add(Track(Dir.North, pointAtDir, 1))
            }
        }
//        println("nextInLoop")
        nextInLoop.alsoPrintOnLines()

        val trackSoFar = mutableListOf(nextInLoop.first())
        val trackSoFar2 = mutableListOf(nextInLoop.last())
        trackSoFar.add(0, Track(nextInLoop.last().from.opposite(), start, 0))

        while (nextInLoop.first().current != nextInLoop.last().current) {
            nextInLoop.add(nextInLoop(input, nextInLoop.removeAt(0)).also { trackSoFar.add(it) })
            nextInLoop.add(nextInLoop(input, nextInLoop.removeAt(0)).also { trackSoFar2.add(it) })
//            println("nextInLoop")
//            nextInLoop.alsoPrintOnLines()
        }

        trackSoFar.addAll(trackSoFar2)
        trackSoFar.alsoPrintOnLines()

        val newLines = mutableListOf<MutableList<Char>>()
        input.forEachIndexed { indexR, s ->
            var s1 = ""
            var s2 = ""
            var s3 = ""
            s.mapIndexed { indexC, c ->
                val char = input[indexR][indexC]
                when (char) {
                    '.' -> {
                        s1 += "..."
                        s2 += "..."
                        s3 += "..."
                    }
                    '|' -> {
                        s1 += ".|."
                        s2 += ".|."
                        s3 += ".|."
                    }
                    '-' -> {
                        s1 += "..."
                        s2 += "---"
                        s3 += "..."
                    }
                    'F' -> {
                        s1 += "..."
                        s2 += ".--"
                        s3 += ".|."
                    }
                    '7' -> {
                        s1 += "..."
                        s2 += "--."
                        s3 += ".|."
                    }
                    'L' -> {
                        s1 += ".|."
                        s2 += ".--"
                        s3 += "..."
                    }
                    'J' -> {
                        s1 += ".|."
                        s2 += "--."
                        s3 += "..."
                    }
                    'S' -> {
                        s1 += "SSS"
                        s2 += "SSS"
                        s3 += "SSS"
                    }
                }
//                print(char)
//                if (trackSoFar.any { it.current.x == indexC && it.current.y == indexR }) {
//                } else {
//                    print(' ')
//                }

            }
//            println(s1)
//            println(s2)
//            println(s3)
            newLines.add(s1.toMutableList())
            newLines.add(s2.toMutableList())
            newLines.add(s3.toMutableList())

//            println()
        }
        var removed = 0
        val neighborsToCheck = mutableListOf(Point(0, 0))
        newLines.first()[0] = ' '
        while (neighborsToCheck.isNotEmpty()) {
            val checking = neighborsToCheck.removeFirst()
            Dir.entries.forEach {
                val newChecking = checking + it.mod
                if (newLines.first().indices.contains(newChecking.x) && newLines.indices.contains(newChecking.y)) {
//                    if (trackSoFar.none { it.current.x == newChecking.x/3 && it.current.y == newChecking.y/3}) {
                    if (newLines[newChecking.y][newChecking.x] == ' ') {
                        // nothin
                    } else if (newLines[newChecking.y][newChecking.x] == '.') {
                        newLines[newChecking.y][newChecking.x] = ' '
                        neighborsToCheck.add(newChecking)
                        removed++
                    } else {
                        if (!trackSoFar.any { it.current.x == newChecking.x / 3 && it.current.y == newChecking.y / 3 }) {
//                            println()
                            newLines[newChecking.y][newChecking.x] = ' '
                            neighborsToCheck.add(newChecking)
                            removed++
                        }
                    }
                }
            }
        }
        println("removed $removed")
        newLines.forEach {
            println(it.joinToString(""))
        }
        var countInternal = 0
        input.indices.forEach { r ->
            input.first().indices.forEach { c ->
                if (
                    newLines[r*3  ].subList(c * 3, c * 3 + 3).contains('.') ||
                    newLines[r*3+1].subList(c * 3, c * 3 + 3).contains('.') ||
                    newLines[r*3+2].subList(c * 3, c * 3 + 3).contains('.')
                    ) {
                    if (trackSoFar.none { it.current.x == c && it.current.y == r }) {
                        countInternal++

                    }
                }
            }

        }


        return countInternal.toLong()
    }

    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 8L, true) { part1(testInput) }
//    solution("Part2 test: ", 10L, true) { part2(readInput("$dir/test_input2")) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ") { part1(input) }
    solution("Part2 solution: ") { part2(input) }
}
