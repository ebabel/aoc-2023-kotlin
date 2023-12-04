import java.math.BigInteger
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.readLines

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

fun Any?.println() = this.also{ println(it) }

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

