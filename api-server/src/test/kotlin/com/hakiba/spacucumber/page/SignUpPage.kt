package com.hakiba.spacucumber.page

import com.hakiba.spacucumber.Browser
import com.hakiba.spacucumber.base.BasePage

/**
 * @author hakiba
 */
class SignUpPage(
        url: String,
        browser: Browser
) : BasePage(url, browser) {
    fun input(mailAddress: String, password: String) {
        browser.input("e2e-mailaddress", mailAddress)
        browser.input("e2e-password", password)
    }

    fun submit() {
        browser.submit("e2e-form")
    }
}
