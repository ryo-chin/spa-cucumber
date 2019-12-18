package com.hakiba.spacucumber

import kotlin.system.measureTimeMillis

/**
 * @author hakiba
 */
object TimeUtils {
    fun waitUntil(limit: Int, interval: Int, fn: () -> Boolean) : Boolean {
        var waitMills = 0L
        var done = false
        while (waitMills < limit) {
            done = fn()
            if (done) {
                break
            }
            waitMills += measureTimeMillis { Thread.sleep(interval.toLong()) }
        }
        return done
    }
}