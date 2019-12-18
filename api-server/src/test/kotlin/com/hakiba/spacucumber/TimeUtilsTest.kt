package com.hakiba.spacucumber

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * @author hakiba
 */
class TimeUtilsTest {

    @Test
    fun test_waitUntil() {
        var count = 0
        var done = TimeUtils.waitUntil(5000, 500) {
            count++
            println("count=$count")
            count == 5
        }
        assertThat(done).isTrue()
        assertThat(count).isEqualTo(5)

        count = 0
        done = TimeUtils.waitUntil(5000, 500) {
            count++
            println("count=$count")
            count == -1
        }
        assertThat(done).isFalse()
    }
}
