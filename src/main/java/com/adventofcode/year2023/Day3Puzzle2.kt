package main.java.com.adventofcode.year2023

import main.java.com.adventofcode.PuzzleUtil
import java.lang.Integer.max
import java.lang.Integer.min

/**
--- Part Two ---
The engineer finds the missing part and installs it in the engine! As the engine springs to life, you jump in the closest gondola, finally ready to ascend to the water source.

You don't seem to be going very fast, though. Maybe something is still wrong? Fortunately, the gondola has a phone labeled "help", so you pick it up and the engineer answers.

Before you can explain the situation, she suggests that you look out the window. There stands the engineer, holding a phone in one hand and waving with the other. You're going so slowly that you haven't even left the station. You exit the gondola.

The missing part wasn't the only issue - one of the gears in the engine is wrong. A gear is any * symbol that is adjacent to exactly two part numbers. Its gear ratio is the result of multiplying those two numbers together.

This time, you need to find the gear ratio of every gear and add them all up so that the engineer can figure out which gear needs to be replaced.

Consider the same engine schematic again:

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

In this schematic, there are two gears. The first is in the top left; it has part numbers 467 and 35, so its gear ratio is 16345. The second gear is in the lower right; its gear ratio is 451490. (The * adjacent to 617 is not a gear because it is only adjacent to one part number.) Adding up all of the gear ratios produces 467835.

What is the sum of all of the gear ratios in your engine schematic?
 */
fun main() {
    val problem = PuzzleUtil.readProblem("2023", "day_3.txt")
    val puzzleSolution = Day3Puzzle2().solvePuzzle(problem)
    println("Puzzle 2 solution: %s".format(puzzleSolution))
}

class Day3Puzzle2 {

    private val multiDigitNumberRegex = "(\\d+)".toRegex()
    private val symbolRegex = "([^.|0-9])".toRegex()

    fun solvePuzzle(engineSchematic: List<String>): Int {
        val adjacentPartsBySymbol = getAdjacentPartsBySymbol(engineSchematic)
        return adjacentPartsBySymbol.entries.stream()
            .filter { it.key.symbol == "*" && it.value.size > 1 }
            .map { getGearRatio(it.value) }
            .reduce(Integer::sum)
            .get()
    }

    private fun getGearRatio(parts: List<Part>): Int {
        return parts.stream()
            .map(Part::partNumber)
            .reduce { a, b -> a * b }
            .get()
    }

    private fun getAdjacentPartsBySymbol(engineSchematic: List<String>): HashMap<Symbol, List<Part>> {
        val allParts = arrayListOf<Part>()
        val allSymbols = arrayListOf<Symbol>()
        for ((index, row) in engineSchematic.withIndex()) {
            allParts.addAll(getAllParts(row, index, engineSchematic.size))
            allSymbols.addAll(getAllSymbols(row, index))
        }
        return getAdjacentPartsBySymbol(allParts, allSymbols)
    }

    private fun getAdjacentPartsBySymbol(
        parts: ArrayList<Part>,
        symbols: ArrayList<Symbol>
    ): HashMap<Symbol, List<Part>> {
        val adjacentPartsBySymbol = HashMap<Symbol, List<Part>>()
        for (symbol in symbols) {
            adjacentPartsBySymbol[symbol] = getAdjacentParts(symbol, parts)
        }
        return adjacentPartsBySymbol
    }

    private fun getAdjacentParts(symbol: Symbol, parts: java.util.ArrayList<Part>): ArrayList<Part> {
        val adjacentParts = arrayListOf<Part>()
        for (part in parts) {
            if (part.rowRange.contains(symbol.rowIndex) && part.colRange.contains(symbol.colIndex)) {
                adjacentParts.add(part)
            }
        }
        return adjacentParts
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

