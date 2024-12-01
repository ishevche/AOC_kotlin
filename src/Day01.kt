import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val (loc1, loc2) = input.map { line ->
            line.split(" ")
                .filter { it.isNotBlank() }
                .map { it.toInt() }
                .let { it[0] to it[1] }
        }.unzip().run { first.sorted() to second.sorted() }
        return loc1.zip(loc2).sumOf { (a, b) -> abs(a - b) }
    }

    fun part2(input: List<String>): Int {
        val (loc1, loc2) = input.map { line ->
            line.split(" ")
                .filter { it.isNotBlank() }
                .map { it.toInt() }
                .let { it[0] to it[1] }
        }.unzip()
        return loc1.sumOf { one -> loc2.count { two -> one == two } * one }
    }

    val testInput = readInput("sample")
    check(part1(testInput) == 11) { part1(testInput) }
    check(part2(testInput) == 31) { part2(testInput) }
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
