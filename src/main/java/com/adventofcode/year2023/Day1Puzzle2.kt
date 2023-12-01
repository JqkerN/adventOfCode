package main.java.com.adventofcode.year2023

import main.java.com.adventofcode.PuzzleUtil
import java.util.function.BiFunction

/**
Your calculation isn't quite right. It looks like some of the digits are actually spelled out with letters: one, two, three, four, five, six, seven, eight, and nine also count as valid "digits".

Equipped with this new information, you now need to find the real first and last digit on each line. For example:
```
two1nine
eightwothree
abcone2threexyz
xtwone3four
4nineeightseven2
zoneight234
7pqrstsixteen
```
In this example, the calibration values are 29, 83, 13, 24, 42, 14, and 76. Adding these together produces 281.

What is the sum of all of the calibration values?
 */
fun main() {
    val problem = PuzzleUtil.readProblem("2023", "day_1.txt")
    val puzzleSolution = Day1Puzzle2().solvePuzzle(problem)
    println("Puzzle 2 solution: %s".format(puzzleSolution))
}

class Day1Puzzle2 {
    private val allNumberStringRepresentations = mapOf(
        Pair("one", "1"),
        Pair("two", "2"),
        Pair("three", "3"),
        Pair("four", "4"),
        Pair("five", "5"),
        Pair("six", "6"),
        Pair("seven", "7"),
        Pair("eight", "8"),
        Pair("nine", "9")
    )
    private val stringBuilder: (t: String, u: Char) -> String = { sequence, char -> sequence + char }
    private val reversedStringBuilder: (t: String, u: Char) -> String = { sequence, char -> char + sequence }


    fun solvePuzzle(calibrationRows: List<String>): Int {
        var result = 0
        for (calibrationRow in calibrationRows) {
            val firstNumber = extractFirstOccurringNumber(
                calibrationRow,
                allNumberStringRepresentations,
                stringBuilder
            )
            val lastNumber = extractFirstOccurringNumber(
                calibrationRow.reversed(),
                allNumberStringRepresentations,
                reversedStringBuilder
            )
            val twoDigitNumber = String.format("%s%s", firstNumber, lastNumber)
            result += twoDigitNumber.toInt()
        }
        return result
    }

    private fun extractFirstOccurringNumber(
        calibrationRow: String,
        numberStringRepresentations: Map<String, String>,
        iteratedStringBuilder: BiFunction<String, Char, String>
    ): String {
        var iteratedString = ""
        for (char in calibrationRow) {
            if (char.isDigit()) {
                return char.toString()
            }
            iteratedString = iteratedStringBuilder.apply(iteratedString, char)
            val number = getNumberIfStringRepresentationIsPresent(iteratedString, numberStringRepresentations)
            if (number != null) {
                return number
            }
        }
        return ""
    }

    private fun getNumberIfStringRepresentationIsPresent(
        sequence: String,
        numberStringRepresentations: Map<String, String>
    ): String? {
        val key = numberStringRepresentations.keys.stream()
            .filter(sequence::contains)
            .findFirst()
            .orElse(null)
        return numberStringRepresentations[key]
    }
}




