package test.java.com.adventofcode.year2023

import main.java.com.adventofcode.year2023.Day1Puzzle1
import main.java.com.adventofcode.year2023.Day1Puzzle2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class Day1Test {

    @Test
    fun shouldSolvePuzzle1GivenExampleProblem() {
        val testProblem = TestProblemReader().getTestProblemInput(
            """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
            """
        )
        val solution = Day1Puzzle1().solvePuzzle(testProblem)

        assertEquals(142, solution)
    }

    @Test
    fun shouldSolvePuzzle2GivenExampleProblem() {
        val testProblem = TestProblemReader().getTestProblemInput(
            """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
            """
        )
        val solution = Day1Puzzle2().solvePuzzle(testProblem)

        assertEquals(281, solution)
    }
}