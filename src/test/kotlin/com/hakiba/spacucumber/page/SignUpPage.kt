package com.hakiba.spacucumber.page

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
}
