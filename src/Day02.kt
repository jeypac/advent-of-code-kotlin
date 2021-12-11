fun main() {
    fun part1(input: List<String>): Int {
        var x = 0
        var depth = 0
        for (s in input) {
            if (s.startsWith("forward")) {
                x += s.secondInt()
            } else if (s.startsWith("down")) {
                depth += s.secondInt()
            } else if (s.startsWith("up")) {
                depth -= s.secondInt()
            }
        }
        return x * depth
    }

    fun part2(input: List<String>): Int {
        var x = 0
        var aim = 0
        var depth = 0
        for (s in input) {
            if (s.startsWith("forward")) {
                x += s.secondInt()
                depth += aim * s.secondInt()
            } else if (s.startsWith("down")) {
                aim += s.secondInt()
            } else if (s.startsWith("up")) {
                aim -= s.secondInt()
            }
        }
        return x * depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput01 = readInput("Day02_test01")
    check(part1(testInput01) == 150)

    // test if implementation meets criteria from the description, like:
    val testInput02 = readInput("Day02_test02")
    check(part2(testInput02) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

fun String.secondInt(): Int {
    return split(" ".toRegex())[1].toInt()
}
