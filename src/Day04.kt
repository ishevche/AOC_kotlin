fun main() {
    fun matches(
        letters: List<CharArray>, word: String, start: Pair<Int, Int>, direction: Pair<Int, Int>, index: Int
    ): Boolean {
        if (index >= word.length) return true
        val i = start.first + direction.first * index
        val j = start.second + direction.second * index
        if (i < 0 || i >= letters.size || j < 0 || j >= letters[0].size) return false
        if (letters[i][j] != word[index]) return false
        return matches(letters, word, start, direction, index + 1)
    }

    fun part1(input: List<String>): Int {
        val letters = input.map { it.toCharArray() }
        val directions = listOf(1, 1, 1, 0, -1, -1, -1, 0).zip(listOf(-1, 0, 1, 1, 1, 0, -1, -1))
        var count = 0
        letters.forEachIndexed { i, row ->
            row.forEachIndexed { j, _ ->
                directions.forEach { direction ->
                    if (matches(letters, "XMAS", i to j, direction, 0)) {
                        count += 1
                    }
                }
            }
        }
        return count
    }

    fun part2(input: List<String>): Int {
        val letters = input.map { it.toCharArray() }
        val directions = listOf(1, 1, -1, -1).zip(listOf(-1, 1, 1, -1))
        var count = 0
        letters.forEachIndexed { i, row ->
            row.forEachIndexed { j, _ ->
                directions.forEach { direction1 ->
                    directions.forEach { direction2 ->
                        val di = direction1.first - direction2.first
                        val dj = direction1.second - direction2.second
                        if (direction1 != direction2 &&
                            matches(letters, "MAS", i to j, direction1, 0) &&
                            matches(letters, "MAS", i + di to j + dj, direction2, 0)
                        ) {

                            count += 1
                        }
                    }
                }
            }
        }
        return count / 2
    }

    val testInput = readInput("sample")
    check(part1(testInput) == 18) { part1(testInput) }
    check(part2(testInput) == 9) { part2(testInput) }
    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
