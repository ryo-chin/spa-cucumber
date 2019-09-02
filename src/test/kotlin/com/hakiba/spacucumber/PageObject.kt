package com.hakiba.spacucumber

import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

private val driver = RemoteWebDriver(URL(seleniumServerUrl), DesiredCapabilities.chrome())

val signUpPO = SignUpPageObject(driver)

class SignUpPageObject(
        private val driver: RemoteWebDriver
) {
    fun visit(url: String) {
        driver.get(url)
    }

    fun input(className: String, value: String) {
        driver.findElementByClassName(className).sendKeys(value)
    }

    fun submit(className: String) {
        driver.findElementByClassName(className).submit()
    }
}

fun currentUrl(): String = driver.currentUrl
