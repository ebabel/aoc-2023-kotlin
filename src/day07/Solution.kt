package day07

import alsoPrintOnLines
import println
import readInput
import solution

private val List<Int>.score: Int
    get() {
        val groupedBy = groupBy { it }
        val groupedByList = groupedBy.map { it.value.count() }.toList().sortedDescending()

        val count = groupedByList.max()
        return if (count == 5) {
            1
        } else if (count == 4) {
            2
        } else if (groupedByList.size == 2 && groupedByList.first() == 3 && groupedByList.last() == 2) {
            3
        } else if (groupedByList.first() == 3) {
            4
        } else if (groupedByList.first() == 2 && groupedByList[1] == 2) {
            5
        } else if (groupedByList.first() == 2) {
            6
        } else {
            99
        }
    }

fun main() {
    class A;
    val dir = A().javaClass.packageName
    println()
    val groupBy = listOf(1, 1, 1, 2, 2, 2, 2).groupBy { it }
    val count = groupBy.maxBy { it.value.count() }
    println()

    data class Player(val hand: List<Int>, val bid: Int) {
        //        val score = if (hand.groupBy { it }.maxBy { it.key })
        val score: Int
            get() {
                val groupedBy = hand.groupBy { it }
                val groupedByList = groupedBy.map { it.value.count() }.toList().sortedDescending()

                val count = groupedByList.max()
                return if (count == 5) {
                    1
                } else if (count == 4) {
                    2
                } else if (groupedByList.size == 2 && groupedByList.first() == 3 && groupedByList.last() == 2) {
                    3
                } else if (groupedByList.first() == 3) {
                    4
                } else if (groupedByList.first() == 2 && groupedByList[1] == 2) {
                    5
                } else if (groupedByList.first() == 2) {
                    6
                } else {
                    99
                }
            }
        val score2: Int
            get() {
                val groupedBy = hand.groupBy { it }
                val jokersCount = groupedBy[1]?.count() ?: 0
                val nonJokersList = groupedBy.filter { it.key != 1 }.map { it.value.count() }.toList().sortedDescending()
                val groupedByList = groupedBy.map { it.value.count() }.toList().sortedDescending()

                val count = nonJokersList.firstOrNull() ?: 0
                return if (count + jokersCount >= 5) {
                    1
                } else if (count + jokersCount >= 4) {
                    2
                } else if (groupedByList.size == 2 && groupedByList.first() == 3 && groupedByList.last() == 2) {
                    3
                } else if (nonJokersList.firstOrNull() == 2 && nonJokersList[1] == 2 && hand.contains(1)) {
                    3
                } else if (count + jokersCount >= 3) {
                    4
                } else if (groupedByList.first() == 2 && groupedByList[1] + jokersCount >= 2) {
                    5
                } else if (count + jokersCount >= 2) {
                    6
                } else {
                    99
                }
            }
    }

    fun part1(input: List<String>): Long {
//        return
        return input.mapIndexed { index, line ->
            val hand = line.take(5).map {
                if (it.isDigit()) {
                    it.digitToInt()
                } else if (it == 'T') {
                    10
                } else if (it == 'J') {
                    1
                } else if (it == 'Q') {
                    12
                } else if (it == 'K') {
                    13
                } else if (it == 'A') {
                    14
                } else {
                    throw IllegalStateException()
                }
            }

            val bid = line.substringAfter(" ").toInt()
            Player(hand, bid)
        }.sortedWith(compareBy<Player> { it.score }
            .thenByDescending { it.hand.first() }
            .thenByDescending { it.hand[1] }
            .thenByDescending { it.hand[2] }
            .thenByDescending { it.hand[3] }
            .thenByDescending { it.hand[4] }
        )

            .mapIndexed { index, player ->
                println("player $index $player")
                (input.size - index) * player.bid.toLong()
            }.sum()
    }

    fun part2(input: List<String>): Long {
        return input.mapIndexed { index, line ->
            val hand = line.take(5).map {
                if (it.isDigit()) {
                    it.digitToInt()
                } else if (it == 'T') {
                    10
                } else if (it == 'J') {
                    1
                } else if (it == 'Q') {
                    12
                } else if (it == 'K') {
                    13
                } else if (it == 'A') {
                    14
                } else {
                    throw IllegalStateException()
                }
            }

            val bid = line.substringAfter(" ").toInt()
            Player(hand, bid)
        }.sortedWith(compareBy<Player> { it.score2 }
            .thenByDescending { it.hand.first() }
            .thenByDescending { it.hand[1] }
            .thenByDescending { it.hand[2] }
            .thenByDescending { it.hand[3] }
            .thenByDescending { it.hand[4] }
        )

            .mapIndexed { index, player ->
                println("player $index $player")
                (input.size - index) * player.bid.toLong()
            }.sum()
    }

    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 6440L, true) { part1(testInput) }
//    solution("Part2 test: ", 5905L, true) { part2(testInput) }
    val input = readInput("$dir/input")

    // 250628371 not right
//    solution("Part1 solution: ") { part1(input) }

    // 250993895 not right
    solution("Part2 solution: ") { part2(input) }
}
