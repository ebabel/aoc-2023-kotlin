import kotlin.math.pow

fun main() {

    fun part1(input: List<String>): Long {
        return input.map { line ->
            val (my, winning) = line.split("|")
            val myNums = my.trim().substringAfter(":").trim().split(" ").filter { it.isNotEmpty() }
            val winNums = winning.trim().split(" ").filter { it.isNotEmpty() }

            2.0.pow(
                myNums
                    .intersect(winNums)
                    .also {
//                        println(it)
                    }.distinct()
                    .count() - 1
            ).toLong().also {
//                println(it)
            }

        }.sum()
    }

    fun part2(input: List<String>): Long {
        var multiplier = 1L
        val list = ArrayList<Long>(input.size)
        return input.mapIndexed { index, line ->
            val (my, winning) = line.split("|")
            val myNums = my.trim().substringAfter(":").trim().split(" ").filter { it.isNotEmpty() }
            val winNums = winning.trim().split(" ").filter { it.isNotEmpty() }

            val winningNumber = myNums
                .intersect(winNums)
                .also {
//                    println(it)
                }.distinct()
                .count()

            multiplier = 1L + (list.removeFirstOrNull() ?: 0L)

            var i = 0
            repeat(winningNumber) {
                if (i < list.size) {
                    list[i] += multiplier
                } else {
                    list.add(multiplier)
                }
                i++
            }

            multiplier.also {
//                println("${index + 1} $winningNumber $multiplier $list")
            }
        }.sum()
    }


    val testInput = readInput("Day04_test")

    check(part1(testInput).expecting(13L))
    check(part2(testInput).expecting(30L))

    val input = readInput("Day04")
    println("Part1 solution: ${part1(input)}")
    println("Part2 solution: ${part2(input)}")

}
