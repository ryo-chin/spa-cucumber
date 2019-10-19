package com.hakiba.spacucumber.page

import com.hakiba.spacucumber.base.BasePage
import com.hakiba.spacucumber.browser

/**
 * @author hakiba
 */
class SignUpPage : BasePage("/signup") {
    fun input(mailAddress: String, password: String) {
        browser.input("e2e-mailaddress", mailAddress)
        browser.input("e2e-password", password)
    }

    fun submit() {
        browser.submit("e2e-form")
    }
}
