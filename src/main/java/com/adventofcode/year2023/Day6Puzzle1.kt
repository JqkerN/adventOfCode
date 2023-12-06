package main.java.com.adventofcode.year2023

import main.java.com.adventofcode.PuzzleUtil
import java.lang.Math.*
import kotlin.math.pow
import kotlin.math.sqrt

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
            val max = floor(max(roots.first, roots.second) - 0.000001)
            val min = ceil(min(roots.first, roots.second) + 0.000001)
            score *= (max - min + 1).toInt()
        }
        return score
    }

    fun pqFormula(p: Float, q: Float): Pair<Double, Double> {
        val pHalf = p / 2.0
        return Pair(-pHalf + sqrt(pHalf.pow(2) - q), -pHalf - sqrt(pHalf.pow(2) - q))
    }
}

