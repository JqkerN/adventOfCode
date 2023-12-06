package test.java.com.adventofcode.year2023

import main.java.com.adventofcode.year2023.Day6Puzzle1
import main.java.com.adventofcode.year2023.Day6Puzzle2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class Day6Test {

    @Test
    fun shouldSolvePuzzle1GivenExampleProblem() {
        val testProblem = TestProblemReader().getTestProblemInput(
            """
                Time:      7  15   30
                Distance:  9  40  200
            """
        )
        val solution = Day6Puzzle1().solvePuzzle(testProblem)

        assertEquals(288, solution)
    }

    @Test
    fun shouldSolvePuzzle2GivenExampleProblem() {
        val testProblem = TestProblemReader().getTestProblemInput(
            """
                Time:      7  15   30
                Distance:  9  40  200
            """
        )
        val solution = Day6Puzzle2().solvePuzzle(testProblem)

        assertEquals(71503, solution)
    }
}