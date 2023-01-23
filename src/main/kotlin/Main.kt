fun main() {
    val firstLine = readln().split(" ")
    val secondLine = readln()
    val  index = firstLine.indexOf(secondLine)
    if (index == -1) println("Not Found")
    else println(index + 1)
}