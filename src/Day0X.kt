fun main() {


    fun part1(input: List<String>): Int {

        return input.size
    }

    fun part2(input: List<String>): Int {

        return input.size
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
//    val testInput2 = readInput("Day03_test2")
    check(part1(testInput) == 4361)
    check(part2(testInput) == 2286)

    val input = readInput("Day03")
    println("Part1 solution: ${part1(input)}")
    println("Part2 solution: ${part2(input)}")
}
