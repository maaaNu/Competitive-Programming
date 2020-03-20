import java.lang.Integer.max
import java.math.BigInteger
import java.util.*
import kotlin.math.abs
import kotlin.math.min


inline fun <reified T> matrix2d(height: Int, width: Int, initialize: () -> T) =
        Array(height) { Array(width) { initialize() } }

object MediumProblems {

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
            it.map { integer -> print("$integer ") }
            println("|")
        }

        return m[change - 1].flatMap { it.flatten() }.sorted().toSet().size

    }

    /**
     * You are working at the cash counter at a fun-fair, and you have different types of coins available to you
     * in infinite quantities. The value of each coin is already given. Can you determine the number of ways of
     * making change for a particular number of units using the given types of coins?
     * Link: https://www.hackerrank.com/challenges/coin-change/problem
     *
     * @param change - the amount to make change for
     * @param coins - list of integers representing available denominations
     * @return number of possibilities we can get a sum of [change] given infinite supply of the type of [coins].
     */
    fun coinChangeProblem(change: Int, coins: List<Int>): BigInteger {
        val dp = Array(change + 1) { BigInteger.valueOf(0) }
        dp[0] = BigInteger.valueOf(1) // if the remaining value is 0, there is exactly 1 new possibility

        for (coin in coins) {
            for (index in coin..change) {
                dp[index] += dp[index - coin]
            }
        }
        return dp[change]
    }

    /**
     * Given a starting distribution, calculate the minimum number of operations needed so that every colleague has the
     * same number of chocolates.
     * Link: https://www.hackerrank.com/challenges/equal/problem
     *
     * The basic Idea: Giving n pieces of chocolate to everyone but one is the same as to remove n pieces of
     * chocolate from said person.
     * Also, the person with the minimal number of chocolate (n_min) acts as baseline and you can remove that number from
     * everybody else. Once everybody reached 0, you have an even distribution. To minimize the number of steps
     * you should use 5 as often as possible (and then 2 and only then 1). Since this holds true it can be more optimal
     * to reduce n_min by 2 (op2) or 1 (op1).
     * E.g. following distribution [6, 6, 2] => n_min = 2 => [4,4,0] => two op2 per person => 4 operations in total
     * If you reduce n_min by 1, you get [5,5,1] => two op3 operations for the first 2 persons and 1 for the last one =
     * 3 operations in total.
     *
     * @param startDist - starting distribution
     * @return minimal number of operations
     */
    fun chocolateEqual(startDist: List<Int>): Long {
        val min = startDist.min()!!
        var minSteps = Long.MAX_VALUE
        (0..2).asSequence()
                .map { min - it }
                .forEach { baseline ->
                    minSteps = min(minSteps, startDist
                            .map { it - baseline }
                            .map { diff ->
                                val op3 = diff / 5L
                                val op2 = (diff % 5L) / 2L
                                val op1 = ((diff % 5L) % 2L)
                                op3 + op2 + op1
                            }
                            .reduce {x,y -> x + y})
                }
        return minSteps
    }

    /**
     * Link: https://www.hackerrank.com/challenges/sherlock-and-cost/problem
     */
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
        println(MediumProblems.sherlockAndCost(startDist))
    }
}
