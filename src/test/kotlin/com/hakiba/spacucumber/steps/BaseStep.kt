package com.hakiba.spacucumber.steps

import com.hakiba.spacucumber.prepareLogger
import com.hakiba.spacucumber.webDriverUtil
import io.cucumber.java8.En

/**
 * @author hakiba
 */
open class BaseStep : En {
    // TODO: Afterが二重で動いてしまう問題を解決
    init {
        val logger = prepareLogger()

        After { scenario ->
            if (scenario.isFailed) {
                webDriverUtil.currentUrl()?.also { logger.info("Last view url: $it") }
                webDriverUtil.takeScreenShot(scenario.name)?.also { logger.info("Take screenShot: ${it.fileName}") }
            }
            webDriverUtil.quit()
        }
    }
}
