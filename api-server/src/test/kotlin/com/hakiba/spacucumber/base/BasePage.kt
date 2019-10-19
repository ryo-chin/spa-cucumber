package com.hakiba.spacucumber.base

import com.hakiba.spacucumber.browser
import com.hakiba.spacucumber.frontServerHostName
import com.hakiba.spacucumber.prepareLogger

/**
 * @author hakiba
 */
abstract class BasePage(
        endpoint: String
) {
    private val url = "$frontServerHostName$endpoint"
    private val logger = prepareLogger()

    fun visit() {
        if (!browser.isLaunched()) {
            browser.relaunch()
        }
        browser.open(url)
    }

    fun isOpened(): Boolean = browser.waitUntil(10) { browser ->
        browser.currentUrl()
                .let { current ->
                    Regex(url).matches(current).also { logger.info("Current URL check Result: {match: $it, urlPattern: \"$url\", actual: $current}") }
                }
    }
}

