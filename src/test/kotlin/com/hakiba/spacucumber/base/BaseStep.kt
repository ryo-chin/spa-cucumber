package com.hakiba.spacucumber.base

import com.hakiba.spacucumber.prepareLogger
import com.hakiba.spacucumber.webDriverUtil
import io.cucumber.java8.En

/**
 * @author hakiba
 */
abstract class BaseStep : En {
    init {
        val logger = prepareLogger()

        After { scenario ->
            if (scenario.isFailed) {
                webDriverUtil.currentUrl()?.also { logger.info("Last view url: $it") }
                webDriverUtil.takeScreenShot(scenario.name)?.also { logger.info("Take screenShot: ${it.fileName}") }
            }
            webDriverUtil.quit()
            logger.info("Complete CleanUp WebDriver")
        }
    }
}
