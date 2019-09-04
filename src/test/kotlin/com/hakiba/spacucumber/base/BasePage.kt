package com.hakiba.spacucumber.base

import com.hakiba.spacucumber.waitUntil
import com.hakiba.spacucumber.browser
import org.openqa.selenium.remote.RemoteWebDriver

/**
 * @author hakiba
 */
abstract class BasePage(
        private val url: String,
        protected var driver: RemoteWebDriver
) {
    fun visit() {
        if (driver.sessionId == null) {
            driver = browser.relaunch()
        }
        driver.get(url)
    }

    fun isOpened(): Boolean {
        return driver.waitUntil(1) {
            it.currentUrl?.let { currentUrl ->
                println("pattern is $url, actual: $currentUrl")
                Regex(url).matches(currentUrl)
            }
        } ?: false
    }
}
