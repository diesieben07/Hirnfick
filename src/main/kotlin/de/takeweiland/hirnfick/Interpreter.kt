package de.takeweiland.hirnfick

import java.nio.charset.Charset

class Interpreter(private val memory: Memory, private val program: String) {

    private var ptr: Long = 0
    private var iPtr: Int = 0
    private val sysIn = System.`in`.reader(Charset.defaultCharset())

    fun execute() {
        while (iPtr < program.lastIndex) {
            runNext()
        }
    }

    private fun runNext() {
        val instruction = program[iPtr]
        when (instruction) {
            '>' -> ptr++
            '<' -> ptr--
            '+' -> memory[ptr]++
            '-' -> memory[ptr]--
            '.' -> print((memory[ptr].toInt() and 0xFF).toChar())
            ',' -> memory[ptr] = sysIn.read().toByte()
            '[' -> {
                if (memory[ptr].toInt() == 0) {
                    var n = 0
                    wh@ while (true) {
                        iPtr++
                        if (iPtr > program.lastIndex) {
                            return
                        }
                        when (program[iPtr]) {
                            '[' -> n++
                            ']' -> {
                                if (n == 0) {
                                    break@wh
                                } else {
                                    n--
                                }
                            }
                        }
                    }
                }
            }
            ']' -> {
                if (memory[ptr].toInt() != 0) {
                    var n = 0
                    wh@ while (true) {
                        iPtr--
                        if (iPtr < 0) {
                            return
                        }
                        when (program[iPtr]) {
                            ']' -> n++
                            '[' -> {
                                if (n == 0) {
                                    break@wh
                                } else {
                                    n--
                                }
                            }
                        }
                    }
                }
            }
        }
        iPtr++
    }
}
