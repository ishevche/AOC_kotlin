fun main() {
    fun topologicalSort(input: List<Int>, ordering: Map<Int, List<Int>>): List<Int> {
        val visited = HashSet<Int>()
        val sortedList = MutableList(0) { 0 }

        fun dfs(element: Int) {
            if (visited.contains(element)) return
            ordering[element]?.intersect(input.toSet())?.forEach { dfs(it) }
            visited.add(element)
            sortedList.add(element)
        }

        input.forEach { if (!visited.contains(it)) dfs(it) }
        return sortedList
    }

    fun parseInput(input: List<String>): List<Pair<List<Int>, List<Int>>> {
        val ordering = input.filter { inputLine ->
            inputLine.contains("|")
        }.map { relationLine ->
            relationLine.split("|").let { pages ->
                pages[1].toInt() to pages[0].toInt()
            }
        }.groupBy({ it.first }, { it.second }) // group all relations by starting point

        return input.filter { inputLine ->
            inputLine.contains(",")
        }.map { updateLine ->
            updateLine.split(",").map { page ->
                page.toInt()
            }
        }.map { update ->
            update to topologicalSort(update, ordering)
        }
    }

    fun part1(input: List<Pair<List<Int>, List<Int>>>): Int {
        return input.filter {
            it.first == it.second
        }.sumOf {
            it.second[it.second.size / 2]
        }
    }

    fun part2(input: List<Pair<List<Int>, List<Int>>>): Int {
        return input.filter {
            it.first != it.second
        }.sumOf {
            it.second[it.second.size / 2]
        }
    }

    val testInput = parseInput(readInput("sample"))
    check(part1(testInput) == 143) { part1(testInput) }
    check(part2(testInput) == 123) { part2(testInput) }
    val input = parseInput(readInput("Day05"))
    part1(input).println()
    part2(input).println()
}
