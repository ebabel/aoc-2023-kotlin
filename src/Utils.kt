import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.abs
import kotlin.math.pow
import kotlin.time.measureTimedValue

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = Path("src/$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5() = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray()))
    .toString(16)
    .padStart(32, '0')

data class Position(val y: Int, val x: Int)

fun Long.expecting(expectation: Long): Boolean {
    if (this == expectation) {
        println("Answer found! $this")
    } else {
        System.err.println("Expecting $expectation, found $this. Off by ${expectation - this}")
    }
    return this == expectation
}

fun solution(
    title: String,
    expectation: Long? = null,
    shouldFail: Boolean = false,
    test: () -> Long,
) {
    System.err.println(" ")
    measureTimedValue(test).let {
        System.err.println("$title (${it.duration})")
        val output = if (expectation == null || expectation == it.value) {
            "Answer found! ${it.value}"
        } else {
            "Expecting $expectation, found ${it.value}. Off by ${expectation - it.value}"
        }
        if (expectation == it.value) {
            System.err.println(output)
        } else if (shouldFail) {
            error(output)
        } else {
            System.err.println(output)
        }

    }
    System.err.println(" ")
}

fun <T> T.println(): T {
    println(this)
    return this
}

fun <R> List<R>.alsoPrintOnLines(): List<R> {
    printOnLn(this)
    println("size: $size")
    return this
}

fun <R> printOnLn(list: List<R>) {
    list.forEach { println(it) }
}

fun List<Long>.productOf() = reduce { acc, i ->
    acc * i
}

fun List<Int>.productOf() = reduce { acc, i ->
    acc * i
}

data class Point(val x: Int = 0, val y: Int = 0) {
    override fun toString(): String = "[$x, $y]"
}

data class PointL(val x: Long = 0, val y: Long = 0) {
    override fun toString(): String = "[$x, $y]"
}

operator fun PointL.minus(other: PointL): PointL = PointL(x - other.x, y - other.y)
operator fun PointL.plus(other: PointL): PointL = PointL(x + other.x, y + other.y)
operator fun Point.plus(other: Point): Point = Point(x + other.x, y + other.y)
operator fun Point.times(amount: Int): Point = Point(x * amount, y * amount)
operator fun PointL.times(amount: Long): PointL = PointL(x * amount, y * amount)

fun PointL.dist2(x: Long, y: Long): Long {
    val dx = this.x - x
    val dy = this.y - y
    return dx * dx + dy * dy
}

fun PointL.dist2(other: PointL): Long {
    val dx = this.x - other.x
    val dy = this.y - other.y
    return dx * dx + dy * dy
}

fun PointL.manhattanDistance(other: PointL) = abs(other.x - x) + abs(other.y - y)

enum class Direction(val dir: Point) {
    SOUTH(Point(0, 1)),
    NORTH(Point(0, -1)),
    WEST(Point(-1, 0)),
    EAST(Point(1, 0));

    fun opposite() = when (this) {
        SOUTH -> NORTH
        NORTH -> SOUTH
        WEST -> EAST
        EAST -> WEST
    }

}
enum class DirectionL(val dir: PointL) {
    SOUTH(PointL(0, 1)),
    NORTH(PointL(0, -1)),
    WEST(PointL(-1, 0)),
    EAST(PointL(1, 0));

    fun opposite() = when (this) {
        SOUTH -> NORTH
        NORTH -> SOUTH
        WEST -> EAST
        EAST -> WEST
    }

}


fun Long.powLong(n: Long): Long {
    return this.toFloat().pow(n.toFloat()).toLong()
}

fun Int.powLong(n: Long): Long {
    return this.toFloat().pow(n.toFloat()).toLong()
}

fun Int.powLong(n: Int): Long {
    return this.toFloat().pow(n.toFloat()).toLong()
}

fun Int.isEven(): Boolean = this and 1 == 0
fun Int.isOdd(): Boolean = this % 2 == 1

fun <T> List<T>.takeAfter(n: Int): List<T> = takeLast(size - n)

/**
 * Finds all numbers in a string and returns them as a Sequence of a number.
 * https://github.com/nbanman/Play2022/blob/master/src/main/kotlin/org/gristle/adventOfCode/utilities/parsing.kt
 */
inline fun <N : Number> String.getNumbers(crossinline transform: String.() -> N?): Sequence<N> =
    Regex("""(?<!\d)-?\d+""")
        .findAll(this)
        .mapNotNull { it.value.transform() }

/**
 * Finds all numbers in a string and returns them as a Sequence of Int.
 */
fun String.getInts(): Sequence<Int> = getNumbers(String::toIntOrNull)

/**
 * Finds all numbers in a string and returns them as a List of Int.
 */
fun String.getIntList() = getInts().toList()

/**
 * Finds all numbers in a string and returns them as a Sequence of Long.
 */
fun String.getLongs(): Sequence<Long> = getNumbers(String::toLongOrNull)

/**
 * Finds all numbers in a string and returns them as a List of Long.
 */
fun String.getLongList() = getLongs().toList()

fun List<String>.containsPoint(point: Point) =
    first().indices.contains(point.x) &&
            indices.contains(point.y)
