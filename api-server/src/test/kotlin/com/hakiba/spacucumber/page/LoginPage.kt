package com.hakiba.spacucumber.page

import com.hakiba.spacucumber.base.BasePage
import com.hakiba.spacucumber.browser

/**
 * @author hakiba
 */
class LoginPage: BasePage("/login") {
    fun inputEmailAndPassword(email: String, password: String) {
        browser.input("e2e-mailAddress", email)
        browser.input("e2e-password", password)
    }

    fun clickLoginButton() {
        browser.click("e2e-login-button")
    }
}
