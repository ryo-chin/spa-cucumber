package com.hakiba.spacucumber.page

import com.hakiba.spacucumber.waitUntil
import org.openqa.selenium.remote.RemoteWebDriver

/**
 * @author hakiba
 */
abstract class BasePage(
        private val url: String,
        protected val driver: RemoteWebDriver
) {
    fun visit() = driver.get(url)
    fun isOpened(): Boolean {
        return driver.waitUntil(1) {
            it.currentUrl?.let { currentUrl ->
                println("pattern is $url, actual: $currentUrl")
                Regex(url).matches(currentUrl)
            }
        } ?: false
    }
}
