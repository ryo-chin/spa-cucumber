package com.hakiba.spacucumber.base

import com.hakiba.spacucumber.Browser
import com.hakiba.spacucumber.prepareLogger

/**
 * @author hakiba
 */
abstract class BasePage(
        private val url: String,
        protected var browser: Browser
) {
    val logger = prepareLogger()

    fun visit() {
        if (!browser.isLaunched()) {
            browser.relaunch()
        }
        browser.open(url)
    }

    fun isOpened(): Boolean = browser.waitUntil(1) { browser ->
        browser.currentUrl()
                .let { current ->
                    Regex(url).matches(current).also { logger.info("Current URL check Result: {match: $it, urlPattern: \"$url\", actual: $current}") }
                }
    }
}

