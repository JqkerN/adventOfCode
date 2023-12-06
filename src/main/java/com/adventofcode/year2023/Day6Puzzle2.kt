package main.java.com.adventofcode.year2023

import main.java.com.adventofcode.PuzzleUtil
import kotlin.math.*

fun main() {
    val problem = PuzzleUtil.readProblem("2023", "day_6.txt")
    val puzzleSolution = Day6Puzzle2().solvePuzzle(problem)
    val puzzleSolutionByMath = Day6Puzzle2().solvePuzzleWithMath(problem)
    println("Puzzle 2 solution: %s".format(puzzleSolution))
    println("Puzzle 2 solution solved with math: %s".format(puzzleSolutionByMath))
}

class Day6Puzzle2 {
    private val numberRegex = "(\\d+.*)".toRegex()

    fun solvePuzzle(puzzleInput: List<String>): Long {
        val time = numberRegex.find(puzzleInput[0])!!.value.replace("\\s".toRegex(), "").toLong()
        val distance = numberRegex.find(puzzleInput[1])!!.value.replace("\\s".toRegex(), "").toLong()
        return (getMaxRoot(time, distance) - getMinRoot(time, distance) + 1)
    }

    private fun getMaxRoot(time: Long, distance: Long): Long {
        for (t in time downTo 0) {
            if (t * (time - t) > distance) {
                return t
            }
        }
        return 0
    }

    private fun getMinRoot(time: Long, distance: Long): Long {
        for (t in 0 until time) {
            if (t * (time - t) > distance) {
                return t
            }
        }
        return 0
    }

    fun solvePuzzleWithMath(puzzleInput: List<String>): Long {
        val time = numberRegex.find(puzzleInput[0])!!.value.replace("\\s".toRegex(), "").toDouble()
        val distance = numberRegex.find(puzzleInput[1])!!.value.replace("\\s".toRegex(), "").toDouble()
        val roots = pqFormula(-time, distance)
        return (getMaxRoot(roots.first, roots.second) - getMinRoot(roots.first, roots.second) + 1).toLong()
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

    private fun pqFormula(p: Double, q: Double): Pair<Double, Double> {
        val pHalf = p / 2.0
        return Pair(-pHalf + sqrt(pHalf.pow(2) - q), -pHalf - sqrt(pHalf.pow(2) - q))
    }
}

