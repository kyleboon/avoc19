
fun main(args: Array<String>) {
    println((123257 until 647015).count { validV1(it.toString()) })

    println((123257 until 647015).count { validV2(it.toString()) })
}

fun validV1(input: String) : Boolean {
    return input.zipWithNext().all { it.first <= it.second } && input.zipWithNext().any { it.first == it.second }
}

fun validV2(input: String) : Boolean {
    return input.zipWithNext().all { it.first <= it.second } && input.groupBy { it }.any { it.value.size == 2 }
}