package main.java.com.adventofcode.year2023

import main.java.com.adventofcode.PuzzleUtil
import kotlin.collections.ArrayList

/**
--- Part Two ---

The Elf says they've stopped producing snow because they aren't getting any water! He isn't sure why the water stopped; however, he can show you how to get to the water source to check it out for yourself. It's just up ahead!

As you continue your walk, the Elf poses a second question: in each game you played, what is the fewest number of cubes of each color that could have been in the bag to make the game possible?

Again consider the example games from earlier:

```
Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
```
- In game 1, the game could have been played with as few as 4 red, 2 green, and 6 blue cubes. If any color had even one fewer cube, the game would have been impossible.
- Game 2 could have been played with a minimum of 1 red, 3 green, and 4 blue cubes.
- Game 3 must have been played with at least 20 red, 13 green, and 6 blue cubes.
- Game 4 required at least 14 red, 3 green, and 15 blue cubes.
- Game 5 needed no fewer than 6 red, 3 green, and 2 blue cubes in the bag.
- The power of a set of cubes is equal to the numbers of red, green, and blue cubes multiplied together. The power of the minimum set of cubes in game 1 is 48. In games 2-5 it was 12, 1560, 630, and 36, respectively. Adding up these five powers produces the sum 2286.

For each game, find the minimum set of cubes that must have been present. What is the sum of the power of these sets?
 */
fun main() {
    val problem = PuzzleUtil.readProblem("2023", "day_2.txt")
    val puzzleSolution = Day2Puzzle2().solvePuzzle(problem)
    println("Puzzle 1 solution: %s".format(puzzleSolution))
}

class Day2Puzzle2 {
    private val cubeOutcomeRegex = "(\\d+ (green|blue|red))".toRegex()
    private val cubeOutcomeAmountRegex = "(\\d+)".toRegex()
    private val cubeOutcomeColorRegex = "(green|blue|red)".toRegex()

    fun solvePuzzle(gameRecords: List<String>): Int {
        val powers = getPowerForEachGameRound(gameRecords)
        return powers.sum()
    }

    private fun getPowerForEachGameRound(
        gameRecords: List<String>,
    ): ArrayList<Int> {
        val powers = arrayListOf<Int>()
        for (gameRecord in gameRecords) {
            val gameOutcomes = getGameOutcomes(gameRecord)
            powers.add(getPowerOfMaxCubeAmounts(gameOutcomes))
        }
        return powers
    }

    private fun getPowerOfMaxCubeAmounts(gameOutcomes: java.util.ArrayList<java.util.ArrayList<CubeOutcome>>): Int {
        val maxCubeAmountByCubeColor = hashMapOf<CubeColor, Int>()
        gameOutcomes.flatten().forEach { cubeOutcome ->
                maxCubeAmountByCubeColor.compute(cubeOutcome.cubeColor) { _, currentAmount ->
                    getMax(
                        cubeOutcome.amount,
                        currentAmount
                    )
                }
            }
        return maxCubeAmountByCubeColor.values.reduce { a, b -> a * b }
    }

    private fun getMax(amount: Int, currentAmount: Int?): Int {
        if (currentAmount == null || amount > currentAmount) {
            return amount
        }
        return currentAmount
    }

    private fun getGameOutcomes(gameRecord: String): ArrayList<ArrayList<CubeOutcome>> {
        val gameOutcomes = gameRecord.split(":")[1].split(";")
        val outcomes = arrayListOf<ArrayList<CubeOutcome>>()
        gameOutcomes.forEach { gameOutcome -> outcomes.add(getCubeOutcomes(gameOutcome)) }
        return outcomes
    }

    private fun getCubeOutcomes(gameOutcome: String): ArrayList<CubeOutcome> {
        val cubeOutcomes = arrayListOf<CubeOutcome>()
        val matches = cubeOutcomeRegex.findAll(gameOutcome)
        matches.forEach { cubeOutcome ->
            val cubeAmount = cubeOutcomeAmountRegex.find(cubeOutcome.value)!!.value.toInt()
            val cubeColor = cubeOutcomeColorRegex.find(cubeOutcome.value)!!.value
            cubeOutcomes.add(CubeOutcome(CubeColor.from(cubeColor), cubeAmount))
        }
        return cubeOutcomes
    }

    class CubeOutcome(var cubeColor: CubeColor, var amount: Int) {
    }

    enum class CubeColor(val colorString: String) {
        RED("red"), GREEN("green"), BLUE("blue");

        companion object {
            infix fun from(colorString: String): CubeColor = CubeColor.values().first { it.colorString == colorString }
        }
    }
}

