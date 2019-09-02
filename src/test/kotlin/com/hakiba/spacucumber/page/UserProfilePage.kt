package com.hakiba.spacucumber.page

import com.hakiba.spacucumber.frontServerHostname
import org.openqa.selenium.remote.RemoteWebDriver

/**
 * @author hakiba
 */
class UserProfilePage(
        url: String,
        driver: RemoteWebDriver
) : BasePage(url, driver) {
    fun isOpened() : Boolean = driver.currentUrl?.startsWith("$frontServerHostname/user") ?: false
}
