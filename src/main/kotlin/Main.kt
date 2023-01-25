import java.io.File
import java.util.*

private lateinit var peopleList: Array<String>
private val peopleMap = mutableMapOf<String, MutableList<Int>>()

fun main() {
    start()
}

fun start() {
    val searchListLines = mutableListOf<String>()
    var count = 0
    File("src/names.txt").forEachLine { line->
        searchListLines.add(line)

        val hold = line.trim()
        val split = hold.lowercase().split("\\s+".toRegex()).toTypedArray()
        for (word in split) {
            if (peopleMap.containsKey(word)) {
                peopleMap[word]?.add(count)
            } else peopleMap[word] = mutableListOf(count)
        }
        count++
    }
    peopleList = searchListLines.toTypedArray()
    menu()
}

fun menu() {
    val menu = "\n=== Menu ===\n1. Find a person\n2. Print all people\n0. Exit"
    var num: Int
    var exit = false
    do {
        val read = getString(menu)
        num = read.toIntOrNull() ?: getNum(read, true)
        when (num) {
            1 -> search()
            2 -> printAll()
            0 -> exit = true
            else -> println("\nIncorrect option! Try again.")
        }
    } while (!exit)
}

fun find() {
    val search = getString("\nEnter a name or email to search all suitable people.").trim()
    var found = false
    peopleList.forEach {
        if (it.lowercase(Locale.getDefault()).contains(search.lowercase(Locale.getDefault()))) {
            if (!found) found = true
            println(it)
        }
    }
    if (!found) println("No matching people found.")
}

fun search() {
    val search = getString("\nEnter a name or email to search all suitable people.").lowercase().trim()
    if (peopleMap.containsKey(search)) {
        println("${peopleMap[search]?.size} persons found:")
        peopleMap[search]?.forEach { println(peopleList[it]) }
    } else println("No matching people found.")
}

fun printAll() {
    println("\n=== List of people ===")
    peopleList.forEach { println(it) }
}

fun getNum(text: String, defaultMessage: Boolean = false): Int {
    val strErrorNum = " was not a number, please try again: "
    var num = text
    var default = defaultMessage

    do {
        num = getString(if (default) num + strErrorNum else num)
        if (!default) default = true
    } while (!isNumber(num))

    return num.toInt()
}

fun getString(text: String): String {
    println(text)
    return readln()
}

fun isNumber(number: String) = number.toIntOrNull() != null
