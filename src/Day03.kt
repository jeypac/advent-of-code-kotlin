fun main() {
    fun part1(input: List<String>): Int {
        val array = IntArray(input[0].length)
        for (s in input) {
            for (i in array.indices) {
                array[i] += s[i].toString().toInt()
            }
        }

        val gammaRate = array.joinToString("") { if (it >= input.size / 2) "1" else "0" }.toInt(2)
        val epsilonRate = array.joinToString("") { if (it >= input.size / 2) "0" else "1" }.toInt(2)
        return gammaRate * epsilonRate
    }

    fun part2(input: List<String>): Int {
        val bitCount = input[0].length - 1

        var oxygen = input
        for (i in 0..bitCount) {
            if (oxygen.size == 1) {
                break
            } else {
                val ones = oxygen.count { it[i] == '1' }
                val zeros = oxygen.size - ones
                oxygen = oxygen.filter { if (ones >= zeros) it[i] == '1' else it[i] == '0' }
            }
        }

        var co2 = input
        for (i in 0..bitCount) {
            if (co2.size == 1) {
                break
            } else {
                val ones = co2.count { it[i] == '1' }
                val zeros = co2.size - ones
                co2 = co2.filter { if (ones < zeros) it[i] == '1' else it[i] == '0' }
            }
        }

        return oxygen.first().toInt(2) * co2.first().toInt(2)
    }

    // test if implementation meets criteria from the description, like:
    val testInput01 = readInput("Day03_test01")
    check(part1(testInput01) == 198)

    // test if implementation meets criteria from the description, like:
    val testInput02 = readInput("Day03_test02")
//    check(part2(testInput02) == 900)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}