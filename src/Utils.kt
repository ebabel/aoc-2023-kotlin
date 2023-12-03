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

/**
 * The cleaner shorthand for printing output.
 */
fun Any?.println() = println(this)

data class Position(val y: Int, val x: Int)

fun Long.expecting(expectation: Long): Boolean {
    if (this == expectation) {
        println("Answer found! $this")
    } else {
        System.err.println("Expecting $expectation, found $this. Off by ${expectation - this}")
    }
    return this == expectation
}