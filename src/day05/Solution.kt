package day05

import expecting
import getLongList
import readInput
import takeAfter

fun main() {
    class A;
    val dir = A().javaClass.packageName

    val maps = listOf(
        "seed-to-soil map:",
        "soil-to-fertilizer map:",
        "fertilizer-to-water map:",
        "water-to-light map:",
        "light-to-temperature map:",
        "temperature-to-humidity map:",
        "humidity-to-location map:",
    )

    data class Range(val start: Long, val end: Long, val modifier: Long, val map: Int)

    fun part1(input: List<String>): Long {
        val seeds = input.first().getLongList()
        var currentMap = 0
        val ranges = mutableListOf<Range>()
        input.takeAfter(2).mapIndexed { _, line ->
            if (maps.contains(line)) {
                maps.forEachIndexed { index, map ->
                    if (map == line) {
                        currentMap = index
                    }
                }
            } else if (line.isNotBlank()) {

                val (destinationStart, sourceStart, range) = line.getLongList()

                val newRange = Range(
                    start = sourceStart,
                    end = sourceStart + range - 1,
                    modifier = destinationStart - sourceStart,
                    currentMap
                )
                ranges.add(newRange)
            }
        }

        return seeds.minOf { seed ->
            var location = seed
            maps.indices.forEach { mapIndex ->
                val foundOverlaps =
                    ranges.filter { it.map == mapIndex }.filter { it.start <= location && it.end >= location }
                check(foundOverlaps.size < 2)

                foundOverlaps.firstOrNull()?.let {
//                    println("moving from $location by ${it.modifier} to ${location + it.modifier}")
                    location += it.modifier
                }

            }
            location
        }
    }

    fun part2(input: List<String>): Long {
        val seeds =
            input.first().getLongList()
                .chunked(2)
                .map { chunk ->
                    chunk[0].rangeUntil(chunk[0] + chunk[1])
                }//.alsoPrintOnLines()
        var currentMap = 0
        val ranges = mutableListOf<Range>()
        input.takeAfter(2).mapIndexed { _, line ->
            if (maps.contains(line)) {
                maps.forEachIndexed { index, map ->
                    if (map == line) {
                        currentMap = index
                    }
                }
            } else if (line.isNotBlank()) {

                val (destinationStart, sourceStart, range) = line.getLongList()

                val newRange = Range(
                    start = sourceStart,
                    end = sourceStart + range - 1,
                    modifier = destinationStart - sourceStart,
                    currentMap
                )
                ranges.add(newRange)
            }
        }
        val seedsToCheck = mutableListOf<LongRange>()
        val seedsToCheckForNextMapIndex = seeds.toMutableList()
        maps.indices.forEach { mapIndex ->
//            println("map index $mapIndex ${seedsToCheckForNextMapIndex.size}")
            seedsToCheck.clear()
            seedsToCheck.addAll(seedsToCheckForNextMapIndex)
            seedsToCheckForNextMapIndex.clear()

            while (seedsToCheck.isNotEmpty()) {
                var foundOverlap = false
                val seedsA = seedsToCheck.removeAt(0)
                ranges.filter { it.map == mapIndex }.forEach {
                    if (seedsA.first in it.start..it.end && seedsA.last in it.start..it.end) {
                        val range = (seedsA.first + it.modifier)..(seedsA.last + it.modifier)
//                        println("moving $seedsA by ${it.modifier} to $range")
                        foundOverlap = true
                        seedsToCheckForNextMapIndex.add(range)

                    } else if (seedsA.first in it.start..it.end) {
                        val range1 = (seedsA.first + it.modifier)..(it.end + it.modifier)
                        val range2 = (it.end + 1)..(seedsA.last)
//                        println("moving1 $seedsA by ${it.modifier} to $range1 and $range2 because of $it")
                        foundOverlap = true
                        seedsToCheckForNextMapIndex.add(range1)
                        seedsToCheck.add(range2)
                    } else if (seedsA.last in it.start..it.end) {
                        val range1 = (it.start + it.modifier)..(seedsA.last + it.modifier)
                        val range2 = (seedsA.first)..<it.start
//                        println("moving2 $seedsA by ${it.modifier} to $range1 and $range2 because of $it")
                        foundOverlap = true
                        seedsToCheckForNextMapIndex.add(range1)
                        seedsToCheck.add(range2)
                    }
                }
                if (!foundOverlap) {
                    seedsToCheckForNextMapIndex.add(seedsA)
                }
            }
        }
//        println("seedsToCheckForNextMapIndex ${seedsToCheckForNextMapIndex.size}")
        return seedsToCheckForNextMapIndex.minOfOrNull { it.first } ?: -1L
    }

    val testInput = readInput("$dir/test_input")

    check(part1(testInput).expecting(35L))
    check(part2(testInput).expecting(46L))

    val input = readInput("$dir/input")
    println("Part1 solution: ${part1(input).expecting(177942185L)}")
    println("Part2 solution: ${part2(input).expecting(69841803L)}")
}