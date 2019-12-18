package com.hakiba.spacucumber

import java.time.Duration
import kotlin.system.measureTimeMillis

/**
 * @author hakiba
 */
object TimeUtils {
    fun waitUntil(limit: Long, interval: Long, fn: () -> Boolean): Boolean = waitUntil(Duration.ofMillis(limit), Duration.ofMillis(interval), fn)

    fun waitUntil(limit: Duration, interval: Duration, fn: () -> Boolean): Boolean {
        var waitDuration = Duration.ZERO
        var done = false
        while (waitDuration < limit) {
            done = fn()
            if (done) {
                break
            }
            waitDuration = waitDuration.plus(Duration.ofMillis(measureTimeMillis { Thread.sleep(interval.toMillis()) }))
        }
        return done
    }
}