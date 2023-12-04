package day03

import Position
import expecting
import readInput

fun main() {

    fun part1(input: List<String>): Long {

        fun Char.isSymbol(): Boolean {
            return !isDigit() && this != '.'
        }

        var parts = 0
        var workingPart = ""

        input.forEachIndexed { lineIndex, line ->
//            println("line $lineIndex")
            var isPart = false
            line.forEachIndexed { charIndex, c ->


                if (c.isDigit()) {
                    workingPart += c

                    if (lineIndex > 0) {
                        if (charIndex > 0) {
                            if (input[lineIndex - 1][charIndex - 1].isSymbol()) {
                                isPart = true
                            }
                        }
                        if (input[lineIndex - 1][charIndex].isSymbol()) {
                            isPart = true
                        }
                        if (charIndex + 1 < line.count()) {
                            if (input[lineIndex - 1][charIndex + 1].isSymbol()) {
                                isPart = true
                            }
                        }
                    }

                    if (charIndex > 0) {
                        if (input[lineIndex][charIndex - 1].isSymbol()) {
                            isPart = true
                        }
                    }
                    if (charIndex + 1 < line.count()) {
                        if (input[lineIndex][charIndex + 1].isSymbol()) {
                            isPart = true
                        }
                    }

                    if (lineIndex + 1 < input.size) {
                        if (charIndex > 0) {
                            if (input[lineIndex + 1][charIndex - 1].isSymbol()) {
                                isPart = true
                            }
                        }
                        if (input[lineIndex + 1][charIndex].isSymbol()) {
                            isPart = true
                        }
                        if (charIndex + 1 < line.count()) {
                            if (input[lineIndex + 1][charIndex + 1].isSymbol()) {
                                isPart = true
                            }
                        }
                    }

                } else {

                    if (workingPart != "" && isPart) {
                        parts += workingPart.toInt()
                    } else if (workingPart != "") {
//                        println("working part $workingPart was not a part")
                    }
                    workingPart = ""
                    isPart = false
                }


                if (charIndex + 1 == line.count()) {
                    if (workingPart != "" && isPart) {
                        parts += workingPart.toInt()
                    } else if (workingPart != "") {
//                        println("working part $workingPart was not a part")
                    }
                    workingPart = ""
                    isPart = false
                }
//                prevIsDigit = c.isDigit()
            }
        }
        return parts.toLong()
    }

    fun part2(input: List<String>): Long {
        var goodGearRatios = 0L

        input.forEachIndexed { lineIndex, line ->
            line.forEachIndexed { charIndex, c ->

                if (c == '*') {
                    val surrounding = mutableListOf<Position>()
                    if (lineIndex > 0) {
                        if (charIndex > 0) {
                            surrounding += Position(lineIndex - 1, charIndex - 1)
                        }
                        surrounding += Position(lineIndex - 1, charIndex)
                        if (charIndex < line.count()) {
                            surrounding += Position(lineIndex - 1, charIndex + 1)
                        }
                    }

                    if (charIndex > 0) {
                        surrounding += Position(lineIndex, charIndex - 1)
                    }
                    if (charIndex + 1 < line.count()) {
                        surrounding += Position(lineIndex, charIndex + 1)
                    }

                    if (lineIndex + 1 < input.size) {
                        if (charIndex > 0) {
                            surrounding += Position(lineIndex + 1, charIndex - 1)
                        }
                        surrounding += Position(lineIndex + 1, charIndex)
                        if (charIndex + 1 < line.count()) {
                            surrounding += Position(lineIndex + 1, charIndex + 1)
                        }
                    }

                    val gears = surrounding
                        .filter { input[it.y][it.x].isDigit() }
                        .mapNotNull {
                            var num = input[it.y][it.x].toString()
                            var workingDownX = it.x - 1
                            var workingUpX = it.x + 1

                            while (workingDownX >= 0 && input[it.y][workingDownX].isDigit()) {
                                num = input[it.y][workingDownX] + num
                                workingDownX--
                            }
                            while (workingUpX < line.count() && input[it.y][workingUpX].isDigit()) {
                                num += input[it.y][workingUpX]
                                workingUpX++
                            }
                            if (num.isNotEmpty()) {
                                num.toLong()
                            } else {
                                null
                            }
                        }.distinct()

                    if (gears.size == 2) {
                        goodGearRatios += gears.first().toLong() * gears.last().toLong()
                        println("gears found $gears")
                    } else {//if (gears.size > 2) {
                        println("gears found $gears")
                    }
                }

            }
        }
        return goodGearRatios
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
//    val testInput2 = readInput("Day03_test2")
//    check(part1(testInput) == 4361)
    check(part2(testInput).expecting(467835L))

    val input = readInput("Day03")
//    println("Part1 solution: ${part1(input)}")
    // your answer is too low 77111577
    // your answer is too low 77351277
    println("Part2 solution: ${part2(input)}")
}
