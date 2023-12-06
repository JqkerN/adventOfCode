package main.java.com.adventofcode.year2023

import main.java.com.adventofcode.PuzzleUtil
import kotlin.math.*

fun main() {
    val problem = PuzzleUtil.readProblem("2023", "day_6.txt")
    val puzzleSolution = Day6Puzzle1().solvePuzzle(problem)
    println("Puzzle 1 solution: %s".format(puzzleSolution))
}

class Day6Puzzle1 {
    private val numberRegex = "(\\d+)".toRegex()

    fun solvePuzzle(puzzleInput: List<String>): Int {
        var score = 1
        val times = numberRegex.findAll(puzzleInput[0]).map { it.value.toFloat() }.toList()
        val distances = numberRegex.findAll(puzzleInput[1]).map { it.value.toFloat() }.toList()
        for ((index, time) in times.withIndex()) {
            val roots = pqFormula(-time, distances[index])
            score *= (getMaxRoot(roots.first, roots.second) - getMinRoot(roots.first, roots.second) + 1).toInt()
        }
        return score
    }

    private fun getMaxRoot(r1: Double, r2: Double): Double {
        var max = max(r1, r2)
        if (max.rem(1).equals(0.0)) {
            max -= 0.1
        }
        return floor(max)
    }

    private fun getMinRoot(r1: Double, r2: Double): Double {
        var min = min(r1, r2)
        if (min.rem(1).equals(0.0)) {
            min += 0.1
        }
        return ceil(min)
    }

    fun pqFormula(p: Float, q: Float): Pair<Double, Double> {
        val pHalf = p / 2.0
        return Pair(-pHalf + sqrt(pHalf.pow(2) - q), -pHalf - sqrt(pHalf.pow(2) - q))
    }
}

