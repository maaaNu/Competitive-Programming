import java.lang.Integer.max
import java.lang.Math.abs
import java.math.BigInteger
import java.util.*


inline fun <reified T> matrix2d(height: Int, width: Int, initialize: () -> T) =
        Array(height) { Array(width) { initialize() } }

object BeginnerProblems {

    fun coinChangeProblemPrintPossibilities(change: Int, values: Set<Int>): Int {
        val m: Array<Array<Set<List<Int>>>> = matrix2d(change, change) { emptySet<List<Int>>() }

        values.takeWhile { it <= change }
                .forEach { m[it - 1][0] = setOf(listOf(it)) }

        for (maxValue in 2..change) {
            for (maxItems in 2..change) {
                for (coinValue in values) {
                    if (coinValue > maxValue) break
                    val remainingValue = maxValue - coinValue
                    if (remainingValue == 0) m[maxValue - 1][maxItems - 1] += setOf(listOf(coinValue))
                    else {
                        if (remainingValue > -1) {
                            m[maxValue - 1][maxItems - 1] += m[remainingValue - 1][maxItems - 2].map { list -> list.toMutableList() + coinValue }.toSet()
                        }
                    }
                }
            }
        }

        m.map {
            it.map { print("$it ") }
            println("|")
        }

        return m[change - 1].flatMap { it.flatten() }.sorted().toSet().size

    }

    fun coinChangeProblem(change: Int, coins: List<Int>): BigInteger {
        val dp = Array(change + 1) { BigInteger.valueOf(0) }
        dp[0] = BigInteger.valueOf(1) //if the remaining value is 0, there is exactly 1 new possibility

        for (coin in coins) {
            for (i in coin..change) {
                dp[i] += dp[i - coin]
            }
        }
        return dp[change]
    }

    fun chocolateEqual(startDist: List<Int>): Long {
        val min = startDist.min()!!
        var minSteps = Long.MAX_VALUE
        (0..2).asSequence()
                .map { min - it }
                .forEach { baseline ->
                    minSteps = Math.min(startDist
                            .map { it - baseline }
                            .map { diff ->
                                val op3 = diff / 5L
                                val op2 = (diff % 5L) / 2L
                                val op1 = ((diff % 5L) % 2L) / 1L
                                op3 + op2 + op1
                            }
                            .reduce {x,y -> x + y}, minSteps)
                }
        return minSteps
    }

    fun sherlockAndCost(b: List<Int>) : Int {
        var endLow = 0
        var endHigh = 0
        (1 until b.size).asSequence()
                .forEach {
                    val low2high = abs(b[it] - 1)
                    val high2high =  abs(b[it] - b[it - 1])
                    val high2low = abs(b[it-1] - 1)

                    val tmpLow = max(endLow, high2low + endHigh)
                    endHigh = max(high2high + endHigh, low2high + endLow)
                    endLow = tmpLow
                }
        return max(endLow, endHigh)
    }

}

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    (0 until scanner.nextInt()).forEach {
        val startDist = (0 until scanner.nextInt()).asSequence().map { scanner.nextInt() }.toList()
        println(BeginnerProblems.sherlockAndCost(startDist))
    }
}
