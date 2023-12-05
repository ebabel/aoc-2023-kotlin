package day05

import alsoPrintOnLines
import expecting
import println
import readInput
import takeAfter
import java.lang.Long.min

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
        val seedStrings = input.first().split(" ")
        val seeds = seedStrings.takeAfter(1).map { it.toLong() }
        var currentMap = 0
        val ranges = mutableListOf<Range>()
        input.takeAfter(2).mapIndexed { index, line ->
            if (maps.contains(line)) {
                maps.forEachIndexed { index, map ->
                    if (map == line) {
                        currentMap = index
                    }
                }
            } else if (line.isNotBlank()) {

                val (destinationStart, sourceStart, range) = line.split(" ").map { it.toLong() }

                val newRange = Range(
                    start = sourceStart,
                    end = sourceStart + range - 1,
                    modifier = destinationStart - sourceStart,
                    currentMap
                )
                ranges.add(newRange)
//                val foundOverlaps = ranges.filter { it.start >= sourceStart && it.start <= newRange.end || it.end >= newRange.start && it.end <= newRange.end}


            }

        }

//        ranges.alsoPrintOnLines()
        return seeds.map { seed -> //.forEach {
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
        }.min()

    }

    // 1997594928 2192252667
    // 2044765513

    fun part2(input: List<String>): Long {
        val seedStrings = input.first().split(" ")
        val seeds = seedStrings.takeAfter(1).map { it.toLong() }.chunked(2).map { chunk ->
            chunk[0].rangeUntil(chunk[0] + chunk[1])
        }.alsoPrintOnLines()
        var currentMap = 0
        val ranges = mutableListOf<Range>()
        input.takeAfter(2).mapIndexed { index, line ->
            if (maps.contains(line)) {
                maps.forEachIndexed { index, map ->
                    if (map == line) {
                        currentMap = index
                    }
                }
            } else if (line.isNotBlank()) {

                val (destinationStart, sourceStart, range) = line.split(" ").map { it.toLong() }

                val newRange = Range(
                    start = sourceStart,
                    end = sourceStart + range - 1,
                    modifier = destinationStart - sourceStart,
                    currentMap
                )
                ranges.add(newRange)
//                val foundOverlaps = ranges.filter { it.start >= sourceStart && it.start <= newRange.end || it.end >= newRange.start && it.end <= newRange.end}


            }

        }
        val seedsToCheck = mutableListOf<LongRange>()
        val seedsToCheckForNextMapIndex = seeds.toMutableList()
        var minOnLastMap = Long.MAX_VALUE
        maps.indices.forEach { mapIndex ->
            println("map index $mapIndex ${seedsToCheckForNextMapIndex.size}")
            seedsToCheck.clear()
            seedsToCheck.addAll(seedsToCheckForNextMapIndex)
            seedsToCheckForNextMapIndex.clear()

            while (seedsToCheck.isNotEmpty()) {
                var foundOverlap = false
                val seedsA = seedsToCheck.removeAt(0)
                ranges.filter { it.map == mapIndex }.forEach {
                    if (seedsA.first in it.start..it.end && seedsA.last in it.start..it.end) {
                        val range = (seedsA.first + it.modifier)..(seedsA.last + it.modifier)
                        println("moving $seedsA by ${it.modifier} to $range")
                        foundOverlap = true
                        seedsToCheckForNextMapIndex.add(range)

                    } else if (seedsA.first in it.start..it.end) {
                        // moving1 0..56926644 by 953587656 to 953587656..960501412 and 6913757..56926644 because of Range(start=0, end=6913756, modifier=953587656, map=3)
                        val range1 = (seedsA.first + it.modifier)..(it.end + it.modifier)
                        val range2 = (it.end + 1)..(seedsA.last)
                        println("moving1 $seedsA by ${it.modifier} to $range1 and $range2 because of $it")
                        foundOverlap = true
                        seedsToCheckForNextMapIndex.add(range1)
                        seedsToCheck.add(range2)
                    } else if (seedsA.last in it.start..it.end) {
                        // seedsA:     6913757..95968287
                        // Range(start=         27095618, end=137355924, modifier=-27095618, map=6)
                        // Range(start=1644649970, end=1826903953, modifier=-1644649970, map=0)

                        // Range(start=27095618, end=137355924, modifier=-27095618, map=6)
                        val range1 = (it.start + it.modifier )..(seedsA.last + it.modifier)
                        val range2 = (seedsA.first )..(it.start-1)
                        println("moving2 $seedsA by ${it.modifier} to $range1 and $range2 because of $it")
                        foundOverlap = true
                        seedsToCheckForNextMapIndex.add(range1)
                        seedsToCheck.add(range2)
                    }
                }//.println() 1609835129-1644649970
                if (!foundOverlap) {
                    seedsToCheckForNextMapIndex.add(seedsA)
                }
            }
        }
        println("seedsToCheckForNextMapIndex ${seedsToCheckForNextMapIndex.size}")
        return seedsToCheckForNextMapIndex.minOfOrNull { it.first } ?: -1L

//        ranges.alsoPrintOnLines()
//        return seeds.map { seed -> //.forEach {
//            var location = seed
//            maps.indices.forEach {mapIndex ->
//                val foundOverlaps = ranges.filter { it.map == mapIndex }.filter { it.start <= location && it.end >= location }
//                check(foundOverlaps.size < 2)
//
//                foundOverlaps.firstOrNull()?.let {
////                    println("moving from $location by ${it.modifier} to ${location + it.modifier}")
//                    location += it.modifier
//                }
//
//            }
//            location
//        }.min()
    }

    val testInput = readInput("$dir/test_input")

//    check(part1(testInput).expecting(35L))
    check(part2(testInput).expecting(46L))

    val input = readInput("$dir/input")
//    println("Part1 solution: ${part1(input)}")
    println("Part2 solution: ${part2(input)}")
}

// 3117212723