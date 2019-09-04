package com.hakiba.spacucumber.base

import com.hakiba.spacucumber.prepareLogger
import com.hakiba.spacucumber.browser
import io.cucumber.java8.En

/**
 * @author hakiba
 */
abstract class BaseStep : En {
    init {
        val logger = prepareLogger()

        After { scenario ->
            if (scenario.isFailed) {
                browser.currentUrl()?.also { logger.info("Last view url: $it") }
                browser.takeScreenShot(scenario.name)?.also { logger.info("Take screenShot: ${it.fileName}") }
            }
            browser.quit()
            logger.info("Quit Browser")
        }
    }
}
