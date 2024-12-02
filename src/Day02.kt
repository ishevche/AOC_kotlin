fun main() {
    fun part1(input: List<String>): Int {
        return input.map { levels ->
            levels.split(" ")
                .map { it.toInt() }
                .zipWithNext { a, b -> a - b }
                .run { min() to max() }
                .run { (1 <= first && second <= 3) || (-3 <= first && second <= -1) }
        }.map { if (it) 1 else 0 }.sum()
    }

    fun part2(input: List<String>): Int {
        val levels = input.map { levels ->
            levels.split(" ")
                .map { it.toInt() }
        }
        return levels.map { level ->
            List(level.size) { idx ->
                level.filterIndexed { index, _ -> index != idx }
            }.map { window ->
                window.zipWithNext { a, b -> a - b }
                    .run { min() to max() }
                    .run { (1 <= first && second <= 3) || (-3 <= first && second <= -1) }
            }.any { it }
        }.map { if (it) 1 else 0 }.sum()
    }


    val testInput = readInput("sample")
    check(part1(testInput) == 2) { part1(testInput) }
    check(part2(testInput) == 4) { part2(testInput) }
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}


