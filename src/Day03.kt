fun main() {
    fun part1(input: List<String>): Int {
        return Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)").findAll(
            input.joinToString("\n")
        ).fold(0) { sum, it ->
            sum + (it.groups[1]?.value?.toInt() ?: 0) *
                    (it.groups[2]?.value?.toInt() ?: 0)
        }
    }

    fun part2(input: List<String>): Int {
        return Regex("(mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\))").findAll(
            input.joinToString("\n")
        ).fold(0 to 1) { (sum, active), it ->
            sum + active *
                    (it.groups[2]?.value?.toInt() ?: 0) *
                    (it.groups[3]?.value?.toInt() ?: 0) to
                    (it.groups[2]?.let { active } ?: run {
                        if (it.groups[0]!!.value == "do()") 1 else 0
                    })
        }.first
    }

    val testInput = readInput("sample")
    check(part1(testInput) == 161) { part1(testInput) }
    check(part2(testInput) == 48) { part2(testInput) }
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
