fun main() {
    fun part1(input: List<String>): Int {
        return 1
    }

    fun part2(input: List<String>): Int {
        return 1
    }

    val testInput = readInput("sample")
    check(part1(testInput) == 1) { part1(testInput) }
    check(part2(testInput) == 1) { part2(testInput) }
    val input = readInput("Day__")
    part1(input).println()
    part2(input).println()
}
