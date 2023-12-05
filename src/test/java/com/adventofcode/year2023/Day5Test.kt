package test.java.com.adventofcode.year2023

import main.java.com.adventofcode.year2023.Day5Puzzle1
import main.java.com.adventofcode.year2023.Day5Puzzle2
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


internal class Day5Test {

    @Test
    fun shouldSolvePuzzle1GivenExampleProblem() {
        val testProblem = TestProblemReader().getTestProblemInput(
            """
                seeds: 79 14 55 13
                
                seed-to-soil map:
                50 98 2
                52 50 48
                
                soil-to-fertilizer map:
                0 15 37
                37 52 2
                39 0 15
                
                fertilizer-to-water map:
                49 53 8
                0 11 42
                42 0 7
                57 7 4
                
                water-to-light map:
                88 18 7
                18 25 70
                
                light-to-temperature map:
                45 77 23
                81 45 19
                68 64 13
                
                temperature-to-humidity map:
                0 69 1
                1 0 69
                
                humidity-to-location map:
                60 56 37
                56 93 4
            """
        )
        val solution = Day5Puzzle1().solvePuzzle(testProblem)

        assertEquals(35, solution)
    }

    @Test
    fun shouldSolvePuzzle2GivenExampleProblem() {
        val testProblem = TestProblemReader().getTestProblemInput(
            """
                seeds: 79 14 55 13
                
                seed-to-soil map:
                50 98 2
                52 50 48
                
                soil-to-fertilizer map:
                0 15 37
                37 52 2
                39 0 15
                
                fertilizer-to-water map:
                49 53 8
                0 11 42
                42 0 7
                57 7 4
                
                water-to-light map:
                88 18 7
                18 25 70
                
                light-to-temperature map:
                45 77 23
                81 45 19
                68 64 13
                
                temperature-to-humidity map:
                0 69 1
                1 0 69
                
                humidity-to-location map:
                60 56 37
                56 93 4
            """
        )
        val solution = Day5Puzzle2().solvePuzzle(testProblem)

        assertEquals(46, solution)
    }
}