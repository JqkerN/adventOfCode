package main.java.com.adventofcode.year2023

import main.java.com.adventofcode.PuzzleUtil
import java.lang.Math.*
import kotlin.math.pow
import kotlin.math.sqrt

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
        val max = floor(max(roots.first, roots.second))
        val min = ceil(min(roots.first, roots.second))
        return (max - min + 1).toLong()
    }

    fun pqFormula(p: Float, q: Float): Pair<Double, Double> {
        val pHalf = p / 2.0
        return Pair(-pHalf + sqrt(pHalf.pow(2) - q), -pHalf - sqrt(pHalf.pow(2) - q))
    }
}

