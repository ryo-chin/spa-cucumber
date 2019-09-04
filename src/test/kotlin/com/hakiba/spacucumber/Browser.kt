package com.hakiba.spacucumber

import org.openqa.selenium.OutputType
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.URL
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * @author hakiba
 */
class Browser(
        private var driver: RemoteWebDriver
) {
    fun isLaunched(): Boolean = driver.sessionId != null

    fun relaunch() {
        this.driver = RemoteWebDriver(URL(seleniumServerUrl), ChromeOptions())
    }

    fun quit() = driver.quit()

    fun open(url: String) {
        driver.get(url)
    }

    fun input(filedName: String, value: String) = driver.findElementByClassName(filedName).sendKeys(value)
    fun submit(name: String) = driver.findElementByClassName(name).submit()

    fun takeScreenShot(fileName: String): Path? {
        if (driver.sessionId == null) {
            return null
        }
        if (Files.notExists(Paths.get(schreeShotFolderPath))) {
            Files.createDirectory(Paths.get(schreeShotFolderPath))
        }
        return Files.write(Paths.get("$schreeShotFolderPath/$fileName.jpg"), driver.getScreenshotAs(OutputType.FILE)!!.readBytes())
    }

    fun currentUrl(): String {
        return driver.currentUrl
    }

    fun <T> waitUntil(second: Long, func: (Browser) -> T): T {
        WebDriverWait(driver, second)
        val result: T = func(this)
        WebDriverWait(driver, 1) // Default is 500 mills, but not exists setter.
        return result
    }
}
