fun main() {


    fun part1(input: List<String>): Long {

        return input.size.toLong()
    }

    fun part2(input: List<String>): Long {

        return input.size.toLong()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day0_test")
//    val testInput2 = readInput("Day0_test2")
    check(part1(testInput).expecting(4361))
    check(part2(testInput).expecting(2286))

    val input = readInput("Day0")
    println("Part1 solution: ${part1(input)}")
    println("Part2 solution: ${part2(input)}")
}
