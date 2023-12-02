package test.java.com.adventofcode.year2023

import main.java.com.adventofcode.year2023.DayXPuzzleY
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class DayXTest {

    @Test
    fun shouldSolvePuzzle1GivenExampleProblem() {
        val testProblem = TestProblemReader().getTestProblemInput(
            """
                
            """
        )
        val solution = DayXPuzzleY().solvePuzzle(testProblem)

        assertEquals(-1, solution)
    }

    @Test
    fun shouldSolvePuzzle2GivenExampleProblem() {
        val testProblem = TestProblemReader().getTestProblemInput(
            """
                
            """
        )
        val solution = DayXPuzzleY().solvePuzzle(testProblem)

        assertEquals(-1, solution)
    }
}