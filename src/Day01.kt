fun main() {
    fun part1(input: List<String>): Int {
        var counter = 0
        for (i in 1 until input.size) {
            if (input[i].toInt() > input[i - 1].toInt()) {
                counter++
            }
        }
        return counter
    }

    fun part2(input: List<String>): Int {
        val intInput = input.map { it.toInt() }
        var counter = 0
        for (i in 0 until intInput.size - 3) {
            val firstSum = intInput[i] + intInput[i + 1] + intInput[i + 2]
            val secondSum = intInput[i + 1] + intInput[i + 2] + intInput[i + 3]
            if (secondSum > firstSum) {
                counter++
            }
        }
        return counter
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 1)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
