package day15

import println
import readInput
import solution
import java.util.LinkedHashMap
import java.util.SortedMap

fun main() {
    class A; val dir = A().javaClass.packageName

    fun String.hash(): Long {
        var current = 0L
        forEach {
    //                it.code.println()
            current += it.code
            current = (current * 17)
            current = current.rem(256)
            current

        }
        return current
    }

    fun part1(input: List<String>): Long {
        return input.first().split(",").sumOf {
            it.hash()
        }

    }

    fun part2(input: List<String>): Long {
        val a = mutableMapOf<Long, LinkedHashMap<String, Int>>()
        input.first().split(",").forEach {
            println()
            if (it.contains('=')) {
                val key = it.substringBefore('=')
                val box = key.hash()
                val value = it.substringAfter('=').toInt()
                if (a.contains(box)) {
                    val sortedMap = a[box]!!
                    sortedMap.println()
                    sortedMap[key] = value
                } else {
                    a[box] = linkedMapOf(key to value)
                }
            } else if (it.contains('-')) {
                val key = it.substringBefore('-')
                val box = key.hash()
                println("need to remove $key from ${a[box]}")
                a[box]?.println()?.remove(key).println()
                println("after remove $a")
            } else error("unknown state")
        }
        a.println()
        return a.map {aEntry ->
            var slot = 0
            aEntry.value.map {
                slot++
                (aEntry.key + 1) * slot * it.value
            }.sum()
        }.sum()
    }
    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 1320L, true) { part1(testInput) }
    solution("Part2 test: ", 145L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ") { part1(input) }
    solution("Part2 solution: ", 258826L, true) { part2(input) }
}
