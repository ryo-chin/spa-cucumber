package com.hakiba.spacucumber

import org.openqa.selenium.OutputType
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * @author hakiba
 */
class WebDriverUtil(
        private val driver: RemoteWebDriver
) {
    fun quit() = driver.quit()

    fun takeScreenShot(fileName: String): Path? {
        if (driver.sessionId == null) {
            return null
        }
        if (Files.notExists(Paths.get(schreeShotFolderPath))) {
            Files.createDirectory(Paths.get(schreeShotFolderPath))
        }
        return Files.write(Paths.get("$schreeShotFolderPath/$fileName.jpg"), driver.getScreenshotAs(OutputType.FILE)!!.readBytes())
    }

    fun currentUrl(): String? {
        if (driver.sessionId == null) {
            return null
        }
        return driver.currentUrl
    }
}

/**
 * Extension
 */
fun <T> RemoteWebDriver.waitUntil(second: Long, func: (RemoteWebDriver) -> T): T {
    WebDriverWait(this, second)
    val result: T = func(this)
    WebDriverWait(this, 1) // Default is 500 mills, but not exists setter.
    return result
}
