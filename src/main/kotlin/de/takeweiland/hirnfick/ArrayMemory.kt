package de.takeweiland.hirnfick

class ArrayMemory : Memory {

    private var bytes: ByteArray = ByteArray(30000)

    private fun Long.index(): Int {
        checkIndex(this)
        return toInt()
    }

    private fun checkIndex(pos: Long) {
        require(pos in 0..29999)
    }

    override fun get(pos: Long): Byte {
        return bytes[pos.index()]
    }

    override fun set(pos: Long, value: Byte) {
        bytes[pos.index()] = value
    }
}