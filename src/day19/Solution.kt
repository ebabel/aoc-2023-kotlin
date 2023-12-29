package day19

import alsoPrintOnLines
import getIntList
import println
import readInput
import solution

sealed class Rules {
    data object Accept : Rules()
    data object Reject : Rules()
    data class Redirect(val to: String) : Rules()
    data class Rule(val param: String, val testType: String, val testVal: Int, val ifTrue: Rules) : Rules()
    data class Data(val x: Int, val m: Int, val a: Int, val s: Int) : Rules()
}

fun checkData(
    ruleset: String,
    rulesProcessed: Map<String, List<Rules>>,
    datum: Rules.Data
): Rules? {
    println("checkData $ruleset")
    rulesProcessed[ruleset]!!.forEach { rule: Rules ->
        val result = checkRule(rule, datum, rulesProcessed)
        if (result is Rules.Reject || result is Rules.Accept) {
            return result
        }
    }
    error("unknown")
    return Rules.Accept // todo
}


fun checkRule(rule: Rules, datum: Rules.Data, rulesProcessed: Map<String, List<Rules>>): Rules? {
    println("checkRule $rule")
    when (rule) {
        is Rules.Rule -> {
            val testParam = when (rule.param) {
                "x" -> datum.x
                "m" -> datum.m
                "a" -> datum.a
                "s" -> datum.s
                else -> error("unknown $rule")
            }

            if (rule.testType == "<") {
                if (testParam < rule.testVal) {
                    return checkRule(rule.ifTrue, datum, rulesProcessed)
                }
            } else if (rule.testType == ">") {
                if (testParam > rule.testVal) {
                    return checkRule(rule.ifTrue, datum, rulesProcessed)
                }
            } else error("$rule")
        }

        is Rules.Redirect -> {
            return checkData(rule.to, rulesProcessed, datum)
        }

        is Rules.Reject, Rules.Accept -> {
            return rule
        }

        else -> error("todo $rule")
    }

    return null // continue processing
}


fun main() {
    class A;
    val dir = A().javaClass.packageName


    fun getRules(it: String): Rules =
        if (it.contains(':')) {

            val (testType, testVal) = if (it.contains("<")) {
                "<" to it.substringAfter("<").substringBefore(":").toInt()
            } else if (it.contains(">")) {
                ">" to it.substringAfter(">").substringBefore(":").toInt()
            } else {
                error("un $it")
            }

            Rules.Rule(
                param = it.take(1),
                testType = testType,
                testVal = testVal,
                getRules(it.substringAfter(":"))
            )
        } else if (it.contains('A')) {
            Rules.Accept
        } else if (it.contains('R')) {
            Rules.Reject
        } else {
            Rules.Redirect(it)
        }


    fun part1(input: List<String>): Long {
        val rulesProcessed = input.mapIndexed { index, line ->
            if (line.contains(":")) {
                val name = line.substringBefore("{")
                val rest = line.substringAfter("{").substringBefore("}")
                name to rest.split(",").map {
                    getRules(it)
                }
            } else {
                null
            }
        }.filterNotNull().alsoPrintOnLines().toMap()
        val data = input.mapIndexed { index, line ->
            if (line.contains(":")) {
                null

            } else if (line.isNotEmpty()) {
                val (x, m, a, s) = line.getIntList()
                Rules.Data(x, m, a, s)
            } else {
                null
            }

        }.filterNotNull().alsoPrintOnLines()


        return data.map { datum: Rules.Data ->
            val result = checkData("in", rulesProcessed, datum)
            if (result is Rules.Accept) {
                datum.x + datum.m + datum.a + datum.s
            } else {
                0
            }


        }.sum().toLong()

    }

    fun part2(input: List<String>): Long {


        val rulesProcessed = input.mapIndexed { index, line ->
            if (line.contains(":")) {
                val name = line.substringBefore("{")
                val rest = line.substringAfter("{").substringBefore("}")
                name to rest.split(",").map {
                    getRules(it)
                }
            } else {
                null
            }
        }.filterNotNull().alsoPrintOnLines().toMap()

        /*

         px: s 1-1350
             qkq: a < 2006
             A: m > 2090


         qqz: s 1351-4000

         */

        data class Node(val ruleSet: String, val rangeSets: List<List<IntRange>>)

        val nodesToProcess = mutableListOf(
            Node(
                "in",
                listOf(
                    mutableListOf(1..4000),
                    mutableListOf(1..4000),
                    mutableListOf(1..4000),
                    mutableListOf(1..4000),
                )
            )
        )

        while (nodesToProcess.isNotEmpty()) {
            val node = nodesToProcess.removeFirst()
            rulesProcessed[node.ruleSet]!!.forEach {
                when (it) {
                    is Rules.Rule -> {
                        // split
                    }

                    is Rules.Accept -> {
                        TODO("multiply")
                    }

                    else -> error("todo $it")
                }
            }

        }

        m.println()


        return input.mapIndexed { index, line ->
            1L
        }.sum()
    }

    val testInput = readInput("$dir/test_input")

//    solution("Part1 test: ", 19114L, true) { part1(testInput) }
    solution("Part2 test: ", 167409079868000L, true) { part2(testInput) }
    val input = readInput("$dir/input")
//    solution("Part1 solution: ") { part1(input) }
    solution("Part2 solution: ") { part2(input) }
}
