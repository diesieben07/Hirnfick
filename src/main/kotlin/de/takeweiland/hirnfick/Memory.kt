package de.takeweiland.hirnfick

interface Memory {

    operator fun get(pos: Long): Byte
    operator fun set(pos: Long, value: Byte)

}