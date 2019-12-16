import java.io.File

fun main(args: Array<String>) {
    var sum = 0
    File("inputs/day01").forEachLine { line: String ->
        val mass = line.toInt()
        sum += fuel(mass)
    }

    println(sum)

}

fun fuel(mass: Int) : Int {
    val f1 = mass / 3 - 2

    if (f1 > 0) {
        return f1 + fuel(f1)
    } else {
        return 0
    }
}