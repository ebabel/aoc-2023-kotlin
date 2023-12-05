import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines
import kotlin.math.pow

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
operator fun Point.minus(other: Point): Point = Point(x - other.x, y - other.y)
operator fun Point.plus(other: Point): Point = Point(x + other.x, y + other.y)
fun Point.dist2(x: Int, y: Int): Int {
    val dx = this.x - x
    val dy = this.y - y
    return dx * dx + dy * dy
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

fun <T> List<T>.takeAfter(n: Int): List<T> = takeLast(size-n)

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