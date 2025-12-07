package stream.techygrrrl.sf6mrcalc.utils

import kotlin.math.abs
import kotlin.math.pow
import kotlin.math.roundToInt

object SF6Utils {

    /**
     * Performs the rebalancing operation to calculate your next phase MR
     * Uses a reverse-engineered Rating Compression Formula / Rating Regression
     *
     * CREDIT: Thanks JB~!
     */
    fun calculateResetMr(mr: Int): Int {
        return (((mr - 1500) * 0.3) + 1500).roundToInt()
    }

    /**
     * Returns the MR that the player (first argument) could take from the opponent (second argument) if the player wins
     */
    fun calculateWinnableMr(playerMr: Int, opponentMr: Int): Int =
        if (playerMr < opponentMr) {
            abs(calculateScore(opponentMr, playerMr, 2))
        } else {
            abs(calculateScore(playerMr, opponentMr, 1))
        }

    /**
     * Returns the MR that the player (first argument) could lose from the opponent (second argument) if the player loses
     */
    fun calculateLosableMr(playerMr: Int, opponentMr: Int): Int =
        if (playerMr < opponentMr) {
            abs(calculateScore(playerMr, opponentMr, 2))
        } else {
            abs(calculateScore(opponentMr, playerMr, 1))
        }

    /**
     * Calculates the Elo rating change for Player 1 (p1).
     *
     * CREDIT: Thanks, Gemini
     *
     * @param p1 Player 1's current rating.
     * @param p2 Player 2's current rating.
     * @param winner The player who won (1 or 2).
     * @return The rounded change in Player 1's rating.
     */
    private fun calculateScore(
        p1: Int,
        p2: Int,
        winner: Int,
        k: Int = 16, // 1. Determine Dynamic K-Factor
    ): Int {

        // 2. Calculate Expected Score for Player 1 (E_a)
        // Formula: 1 / (1 + 10 ^ ((R_b - R_a) / 400))
        val expectedScore = 1.0 / (1.0 + 10.0.pow((p2.toDouble() - p1.toDouble()) / 400.0))

        // 3. Determine Actual Score (S_actual)
        // If winner is 1, P1's actual score is 1.0 (Win), else 0.0 (Loss)
        val actualScore = if (winner == 1) 1.0 else 0.0

        // 4. Calculate Rating Change: K * (S_actual - E_a)
        val ratingChange = k.toDouble() * (actualScore - expectedScore)

        // 5. Return Rounded Integer Result
        return ratingChange.roundToInt()
    }
}
