import java.io.File
import java.lang.Math.abs

fun main(args: Array<String>) {
    val input = File("inputs/day03")
    val wire1: List<Point2D> = parseWire(input.readLines()[0])
    val wire2: List<Point2D> = parseWire(input.readLines()[1])
    val intersections: Set<Point2D> = wire1.intersect(wire2)

    println(intersections.map { it.distanceTo(Point2D.ORIGIN) }.min()!!)

    println(intersections.map { cross ->
            wire1.indexOf(cross) + wire2.indexOf(cross) + 2
        }.min()!!)
}

fun parseWire(line: String): List<Point2D> {
    var current = Point2D.ORIGIN
    return line.split(",").flatMap {
        val direction = it.first()
        val steps = it.drop(1).toInt()
        (0 until steps).map {
            val next = when (direction) {
                'U' -> current.up()
                'D' -> current.down()
                'L' -> current.left()
                'R' -> current.right()
                else -> throw IllegalArgumentException("Invalid direction: $direction")
            }
            current = next
            next
        }
    }
}


data class Point2D(val x: Int, val y: Int) {

    fun up(): Point2D = copy(y = y + 1)
    fun down(): Point2D = copy(y = y - 1)
    fun left(): Point2D = copy(x = x - 1)
    fun right(): Point2D = copy(x = x + 1)

    fun distanceTo(other: Point2D): Int =
        abs(x - other.x) + abs(y - other.y)

    companion object {
        val ORIGIN = Point2D(0, 0)
        val readerOrder: Comparator<Point2D> = Comparator { o1, o2 ->
            when {
                o1.y != o2.y -> o1.y - o2.y
                else -> o1.x - o2.x
            }
        }
    }
}