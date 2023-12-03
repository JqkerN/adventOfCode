package main.java.com.adventofcode.year2023

import main.java.com.adventofcode.PuzzleUtil
import java.lang.Integer.*
import kotlin.collections.ArrayList

/**
--- Day 3: Gear Ratios ---
You and the Elf eventually reach a gondola lift station; he says the gondola lift will take you up to the water source, but this is as far as he can bring you. You go inside.

It doesn't take long to find the gondolas, but there seems to be a problem: they're not moving.

"Aaah!"

You turn around to see a slightly-greasy Elf with a wrench and a look of surprise. "Sorry, I wasn't expecting anyone! The gondola lift isn't working right now; it'll still be a while before I can fix it." You offer to help.

The engineer explains that an engine part seems to be missing from the engine, but nobody can figure out which one. If you can add up all the part numbers in the engine schematic, it should be easy to work out which part is missing.

The engine schematic (your puzzle input) consists of a visual representation of the engine. There are lots of numbers and symbols you don't really understand, but apparently any number adjacent to a symbol, even diagonally, is a "part number" and should be included in your sum. (Periods (.) do not count as a symbol.)

Here is an example engine schematic:

```
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
```

In this schematic, two numbers are not part numbers because they are not adjacent to a symbol: 114 (top right) and 58 (middle right). Every other number is adjacent to a symbol and so is a part number; their sum is 4361.

Of course, the actual engine schematic is much larger. What is the sum of all of the part numbers in the engine schematic?
 */
fun main() {
    val problem = PuzzleUtil.readProblem("2023", "day_3.txt")
    val puzzleSolution = Day3Puzzle1().solvePuzzle(problem)
    println("Puzzle 1 solution: %s".format(puzzleSolution))
}

class Day3Puzzle1 {

    private val multiDigitNumberRegex = "(\\d+)".toRegex()
    private val symbolRegex = "([^.|0-9])".toRegex()

    fun solvePuzzle(engineSchematic: List<String>): Int {
        val parts = getPartsWithAdjacentSymbol(engineSchematic)
        return parts.stream()
            .map(Part::partNumber)
            .reduce(Integer::sum)
            .get()
    }

    private fun getPartsWithAdjacentSymbol(engineSchematic: List<String>): List<Part> {
        val allParts = arrayListOf<Part>()
        val allSymbols = arrayListOf<Symbol>()
        for ((index, row) in engineSchematic.withIndex()) {
            allParts.addAll(getAllParts(row, index, engineSchematic.size))
            allSymbols.addAll(getAllSymbols(row, index))
        }
        return getPartsWithAdjacentSymbol(allParts, allSymbols)
    }

    private fun getPartsWithAdjacentSymbol(
        parts: ArrayList<Part>,
        symbols: ArrayList<Symbol>
    ): List<Part> {
        val filteredParts = arrayListOf<Part>()
        for (part in parts) {
            if (hasAdjacentSymbol(part, symbols)) {
                filteredParts.add(part)
            }
        }
        return filteredParts
    }

    private fun hasAdjacentSymbol(part: Part, symbols: ArrayList<Symbol>): Boolean {
        for (symbol in symbols) {
            if (part.rowRange.contains(symbol.rowIndex) && part.colRange.contains(symbol.colIndex)) {
                return true
            }
        }
        return false
    }

    private fun getAllParts(row: String, rowIndex: Int, maxColIndex: Int): Collection<Part> {
        val parts = arrayListOf<Part>()
        val allNumberMatches: Sequence<MatchResult> = multiDigitNumberRegex.findAll(row)
        for (numberMatch in allNumberMatches) {
            parts.add(
                Part(
                    numberMatch.value.toInt(),
                    IntRange(max(0, numberMatch.range.first - 1), min(row.length, numberMatch.range.last + 1)),
                    IntRange(max(0, rowIndex - 1), min(maxColIndex, rowIndex + 1))
                )
            )
        }
        return parts
    }

    private fun getAllSymbols(row: String, rowIndex: Int): Collection<Symbol> {
        val symbols = arrayListOf<Symbol>()
        val allSymbolMatches: Sequence<MatchResult> = symbolRegex.findAll(row)
        for (numberMatch in allSymbolMatches) {
            symbols.add(Symbol(numberMatch.value, numberMatch.range.first, rowIndex))
        }
        return symbols
    }

    class Part(
        val partNumber: Int,
        val colRange: IntRange,
        val rowRange: IntRange
    ) {
    }

    class Symbol(
        val symbol: String,
        val colIndex: Int,
        val rowIndex: Int
    ) {}
}

