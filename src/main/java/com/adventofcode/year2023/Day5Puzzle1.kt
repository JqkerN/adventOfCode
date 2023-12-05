package main.java.com.adventofcode.year2023

import main.java.com.adventofcode.PuzzleUtil

/**
TLDR
 */
fun main() {
    val problem = PuzzleUtil.readProblem("2023", "day_5.txt")
    val puzzleSolution = Day5Puzzle1().solvePuzzle(problem)
    println("Puzzle 1 solution: %s".format(puzzleSolution))
}

class Day5Puzzle1 {
    private val numberRegex = "(\\d+)".toRegex()

    fun solvePuzzle(puzzleInput: List<String>): Long {
        val seeds = getSeeds(puzzleInput[0])
        val maps = getMaps(puzzleInput.subList(1, puzzleInput.size))
        return getLocationFromSeeds(seeds, maps).min()
    }

    private fun getMaps(puzzleInput: List<String>): ArrayList<IterableMapper> {
        val iterableMappers = arrayListOf<IterableMapper>()
        var mapIndex = -1
        for (row in puzzleInput) {
            if (row.isBlank()) {
                continue
            }
            if (row.contains("map")) {
                mapIndex++
                continue
            }
            val mapperValues = numberRegex.findAll(row).map { it.value.toLong() }.toList()
            if (iterableMappers.lastIndex < mapIndex) {
                iterableMappers.add(IterableMapper(MapType.byIndex(mapIndex)!!, MapType.byIndex(mapIndex + 1), arrayListOf()))
            }
            iterableMappers[mapIndex].addMapper(Mapper(mapperValues[1], mapperValues[0], mapperValues[2]))
        }
        return iterableMappers
    }


    private fun getLocationFromSeeds(
        seeds: List<Long>,
        maps: ArrayList<IterableMapper>
    ): List<Long> {
        val result = arrayListOf<Long>()
        for (seed in seeds) {
            var currentIterableMapper = maps[0]
            var currentValue = currentIterableMapper.map(seed)
            while (currentIterableMapper.hasNext()) {
                currentIterableMapper = maps[currentIterableMapper.nextMapType!!.index]
                currentValue = currentIterableMapper.map(currentValue)
            }
            result.add(currentValue)
        }
        return result
    }

    private fun getSeeds(seedsRow: String): List<Long> {
        return numberRegex.findAll(seedsRow).map { it.value.toLong() }.toList()
    }

    class IterableMapper(val currentMapType: MapType, val nextMapType: MapType?, val mappers: ArrayList<Mapper>) {
        fun hasNext(): Boolean {
            return nextMapType != null
        }

        fun contains(number: Long): Boolean {
            for (mapper in mappers) {
                if (mapper.isMappable(number)) {
                    return true
                }
            }
            return false
        }

        fun map(number: Long): Long {
            for (mapper in mappers) {
                if (mapper.isMappable(number)) {
                    return mapper.mapNumber(number)
                }
            }
            return number
        }

        fun addMapper(mapper: Mapper) {
            this.mappers.add(mapper)
        }
    }

    class Mapper(val startA: Long, val startB: Long, val range: Long) {
        fun isMappable(number : Long): Boolean {
            if (number >= startA && number < startA + range) {
                return true
            }
            return false
        }

        fun mapNumber(number : Long): Long {
            return startB + number - startA
        }
    }

    enum class MapType(val index: Int) {
        SEED_TO_SOIL(0),
        SOIL_TO_FERTILIZER(1),
        FERTILIZER_TO_WATER(2),
        WATER_TO_LIGHT(3),
        LIGHT_TO_TEMPERATURE(4),
        TEMPERATURE_TO_HUMIDITY(5),
        HUMIDITY_TO_LOCATION(6);

        companion object {
            fun byIndex(index: Int): MapType? {
                return values().firstOrNull() { it.index == index }
            }
        }
    }
}

