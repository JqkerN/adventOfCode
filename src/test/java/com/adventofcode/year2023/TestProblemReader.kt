package test.java.com.adventofcode.year2023

class TestProblemReader {
    fun getTestProblemInput(testProblemInput: String): List<String> {
        return testProblemInput.trimIndent().split("\n")
    }
}