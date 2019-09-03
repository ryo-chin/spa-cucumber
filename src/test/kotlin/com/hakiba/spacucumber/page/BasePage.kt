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
    fun isOpened() : Boolean = driver.waitUntil(10) { it.currentUrl?.let { currentUrl -> Regex(url).matches(currentUrl) } ?: false }
}
