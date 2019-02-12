package de.takeweiland.hirnfick

import java.io.File
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val input = if (args.isNotEmpty()) {
        File(args[0]).readText()
    } else {
        System.err.println("file expected")
        exitProcess(-1)
    }
    val memory = ArrayMemory()
    Interpreter(memory, input).execute()
}