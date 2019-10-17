package com.hakiba.spacucumber.spring.filter

import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import javax.servlet.ReadListener
import javax.servlet.ServletInputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper

/**
 * @author hakiba
 */
class BufferedServletInputStream(
        private val inputStream: ByteArrayInputStream
) : ServletInputStream() {
    override fun isReady(): Boolean {
        return false
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun setReadListener(listener: ReadListener?) {

    }

    override fun available(): Int {
        return inputStream.available()
    }

    override fun read(): Int {
        return inputStream.read()
    }

    override fun read(b: ByteArray, off: Int, len: Int): Int {
        return inputStream.read(b, off, len)
    }
}

class BufferedServletRequestWrapper(request: HttpServletRequest) : HttpServletRequestWrapper(request) {

    private val buffer: ByteArray

    override fun getInputStream(): ServletInputStream {
        return BufferedServletInputStream(ByteArrayInputStream(buffer))
    }

    init {
        val inputStream = request.inputStream
        val baos = ByteArrayOutputStream()
        val buff = ByteArray(1024)
        var read: Int = inputStream.read(buff)
        while (read > 0) {
            baos.write(buff, 0, read)
            read = inputStream.read(buff)
        }

        this.buffer = baos.toByteArray()
    }
}