import BeginnerProblems.coinChangeProblem
import BeginnerProblems.chocolateEqual
import BeginnerProblems.sherlockAndCost
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigInteger

class BeginnerProblemsTests {

    @ParameterizedTest
    @MethodSource("coinChangeProblemParameter")
    fun coinChangeProblemTests(expected: Int, change: Int, values: List<Int>) {
        assertThat(coinChangeProblem(change, values)).isEqualTo(expected)
    }

    @ParameterizedTest
    @MethodSource("chocolateEqual")
    fun chocolateEqualTests(expected: Long, startDist: List<Int>) {
        assertThat(expected).isEqualTo(chocolateEqual(startDist))
    }

    @ParameterizedTest
    @MethodSource("sherlockAndCost")
    fun sherlockAndCost(expected: Int, bArray: List<Int>) {
        assertThat(expected).isEqualTo(sherlockAndCost(bArray))
    }

    companion object {
        @JvmStatic
        fun coinChangeProblemParameter () = listOf(
                Arguments.of(4, 4, listOf(1, 2, 3)),
                Arguments.of(5, 10, listOf(2, 5, 3, 6)),
                Arguments.of(10, 15, listOf(49, 22, 45, 6, 11, 20, 30, 10, 46, 8, 32, 48, 2, 41, 43, 5, 39, 16, 28, 44, 14, 4, 27, 36)),
                Arguments.of(101768, 69, listOf(25, 27, 40, 38, 17, 2, 28, 23, 9, 43, 18, 49, 15, 24, 19, 11, 1, 39, 32, 16, 35, 30, 48, 34, 20, 3, 6, 13, 44))
        )

        @JvmStatic
        fun chocolateEqual () = listOf(
                Arguments.of(4L, listOf(1,4,5)),
                Arguments.of(2L, listOf(2, 2, 3, 7)),
                Arguments.of(3L, listOf(1,5,5))
        )

        @JvmStatic
        fun sherlockAndCost () = listOf(
                Arguments.of(36, listOf(10, 1, 10, 1, 10)),
                Arguments.of(396, listOf(100, 2, 100, 2, 100))
        )
    }

    @Test
    fun coinChangeBigTest() {
        assertThat(BigInteger.valueOf(15685693751)).isEqualTo( coinChangeProblem(250, listOf(41, 34, 46, 9, 37, 32, 42, 21, 7, 13, 1, 24, 3, 43, 2, 23, 8, 45, 19, 30, 29, 18, 35, 11)))
    }
}