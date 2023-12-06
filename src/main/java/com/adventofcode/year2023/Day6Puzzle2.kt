package main.java.com.adventofcode.year2023

import main.java.com.adventofcode.PuzzleUtil
import kotlin.math.*

fun main() {
    val problem = PuzzleUtil.readProblem("2023", "day_6.txt")
    val puzzleSolution = Day6Puzzle2().solvePuzzle(problem)
    println("Puzzle 2 solution: %s".format(puzzleSolution - 1))
}

class Day6Puzzle2 {
    private val numberRegex = "(\\d+.*)".toRegex()

    fun solvePuzzle(puzzleInput: List<String>): Long {
        val time = numberRegex.find(puzzleInput[0])!!.value.replace("\\s".toRegex(), "").toFloat()
        val distance = numberRegex.find(puzzleInput[1])!!.value.replace("\\s".toRegex(), "").toFloat()
        val roots = pqFormula(-time, distance)
        return (getMaxRoot(roots.first, roots.second) - getMinRoot(roots.first, roots.second) + 1).toLong()
    }

    private fun getMaxRoot(r1: Double, r2: Double): Double {
        var max = max(r1, r2)
        println(max.toBigDecimal().toPlainString())
        if (max.rem(1).equals(0.0)) {
            max -= 0.1
        }
        return floor(max)
    }

    private fun getMinRoot(r1: Double, r2: Double): Double {
        var min = min(r1, r2)
        println(min.toBigDecimal().toPlainString())
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

