import java.io.File

fun main(args: Array<String>) {
    var noun = 0
    var verb = 0
    var found = false


    for (x in 0..99) {
        for (y in 0..99) {
            noun = x
            verb = y
            val program = File("inputs/day02").readText()
            var memory = program.split(",").map { it.toInt() }.toIntArray()

            memory[1] = noun
            memory[2] = verb

            if (executeIntCode(memory) == 19690720) {
                found = true
                break
            }
        }

        if (found) {
            break
        }
    }


    val answer = 100 * noun + verb
    println(answer)
}

fun executeIntCode(memory: IntArray) : Int {
    var instructionPointer = 0
    var halt = false

    while (!halt) {
        val instruction  = memory[instructionPointer]

        if (instruction == 99) {
            halt = true
        } else {
            val operand1 = memory[memory[instructionPointer+1]]
            val operand2 = memory[memory[instructionPointer+2]]
            val resultLocation = memory[instructionPointer+3]

            var result = if (instruction == 1) {
                operand1 + operand2
            } else if (instruction == 2) {
                operand1 * operand2
            } else {
                throw Exception("PANIC")
            }

            memory[resultLocation] = result
            instructionPointer = instructionPointer+4
        }
    }

    return memory[0]
}