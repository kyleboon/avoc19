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