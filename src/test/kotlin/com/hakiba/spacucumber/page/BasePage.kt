package com.hakiba.spacucumber.page

import org.openqa.selenium.remote.RemoteWebDriver

/**
 * @author hakiba
 */
abstract class BasePage(
        private val url: String,
        protected val driver: RemoteWebDriver
) {
    fun visit() = driver.get(url)
    fun isOpened() : Boolean = driver.currentUrl?.let { Regex(url).matches(it) } ?: false
}
