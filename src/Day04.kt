fun main() {
    fun part1(input: List<String>): Int {
        val numbers = input.first().split(',')

        val border = "            _"
        var boards = input.subList(1, input.size).joinToString(separator = " ,", postfix = border) { it.ifBlank { border } }

        for (number in numbers) {
            val search = number.padStart(2, ' ')
            boards = boards.replace("$search ", "${search}x")

            val bingoPattern = """_[ \dx,]*(([ \d]{2}x){5}|([ \d]{2}x.{13}){5})[ \dx,]*_""".toRegex()
            val board = bingoPattern.find(boards)
            if (board != null) {
                val unmarkedPattern = """\d{1,2} """.toRegex()
                val sumOfUnmarked = unmarkedPattern.findAll(board.value).sumOf { it.value.trim().toInt() }
                return sumOfUnmarked * number.toInt()
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        val numbers = input.first().split(',')

        val border = "            _"
        var boards = input.subList(1, input.size).joinToString(separator = " ,", postfix = border) { it.ifBlank { border } }

        var lastWiningNumber: String? = null
        var lastWiningBoard: String? = null
        for (number in numbers) {
            val search = number.padStart(2, ' ')
            boards = boards.replace("$search ", "${search}x")

            val bingoPattern = """_[ \dx,]*(([ \d]{2}x){5}|([ \d]{2}x.{13}){5})[ \dx,]*_""".toRegex()
            val bingoBoards = bingoPattern.findAll(boards)
            for (bingoBoard in bingoBoards) {
                lastWiningNumber = number
                lastWiningBoard = bingoBoard.value
                boards = boards.replace(lastWiningBoard, border)
            }
        }

        return if (lastWiningNumber != null && lastWiningBoard != null) {
            val unmarkedPattern = """\d{1,2} """.toRegex()
            val sumOfUnmarked = unmarkedPattern.findAll(lastWiningBoard).sumOf { it.value.trim().toInt() }
            sumOfUnmarked * lastWiningNumber.toInt()
        } else {
            0
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput01 = readInput("Day04_test01")
    check(part1(testInput01) == 4512)

    val testInput02 = readInput("Day04_test01")
    check(part2(testInput02) == 1924)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}

fun createBoards(input: List<String>): List<Board> {
    val chunks = input.subList(2, input.size).chunked(6)
    val boards = mutableListOf<Board>()
    for (chunk in chunks) {
        boards.add(Board(chunk.subList(0, 5)))
    }
    return boards
}


class Board(input: List<String>) {

    private val field = Array(6) { IntArray(6) { 0 } }

    init {
        for ((i, s) in input.withIndex()) {
            field[i] = "$s  0".chunked(3).map { it.trim().toInt() }.toIntArray()
        }
    }

    fun mark(number: Int): Int {
        for (i in 0..4) {
            for (j in 0..4) {
                if (field[i][j] == number) {
                    if (++field[i][5] == 5) {
                        return (field[i].sum() - 5) * number
                    }
                    if (++field[5][j] == 5) {
                        return (field.sumOf { it[j] } - 5) * number
                    }
                }
            }
        }
        return 0
    }
}