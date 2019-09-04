package com.hakiba.spacucumber.page

import com.hakiba.spacucumber.base.BasePage
import com.hakiba.spacucumber.userProfilePageUrl
import org.openqa.selenium.remote.RemoteWebDriver

/**
 * @author hakiba
 */
class SignUpPage(
        url: String,
        driver: RemoteWebDriver
) : BasePage(url, driver) {
    fun input(mailAddress: String, password: String) {
        driver.findElementByClassName("e2e-mailaddress").sendKeys(mailAddress)
        driver.findElementByClassName("e2e-password").sendKeys(password)
    }

    fun submit() {
        driver.findElementByClassName("e2e-form").submit()
    }

    fun navigateToUserProfilePage(): UserProfilePage? {
        return driver.takeIf { it.sessionId != null }?.let { UserProfilePage(userProfilePageUrl, it) }
    }
}
