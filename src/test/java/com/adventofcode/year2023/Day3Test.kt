package test.java.com.adventofcode.year2023

import main.java.com.adventofcode.year2023.Day3Puzzle1
import main.java.com.adventofcode.year2023.Day3Puzzle2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class Day3Test {

    @Test
    fun shouldSolvePuzzle1GivenExampleProblem() {
        val testProblem = TestProblemReader().getTestProblemInput(
            """
                467..114..
                ...*......
                ..35..633.
                ......#...
                617*......
                .....+.58.
                ..592.....
                ......755.
                ...$.*....
                .664.598..
            """
        )
        val solution = Day3Puzzle1().solvePuzzle(testProblem)

        assertEquals(4361, solution)
    }

    @Test
    fun shouldSolvePuzzle2GivenExampleProblem() {
        val testProblem = TestProblemReader().getTestProblemInput(
            """
                467..114..
                ...*......
                ..35..633.
                ......#...
                617*......
                .....+.58.
                ..592.....
                ......755.
                ...$.*....
                .664.598..
            """
        )
        val solution = Day3Puzzle2().solvePuzzle(testProblem)

        assertEquals(467835, solution)
    }
}