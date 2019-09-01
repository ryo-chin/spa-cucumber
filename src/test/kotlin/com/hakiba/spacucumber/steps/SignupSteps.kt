package com.hakiba.spacucumber.steps

import io.cucumber.java8.En
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL


/**
 * @author hakiba
 */
class SignupSteps(
        private val frontServerHostname: String = "http://frontserver:4200",
        private val seleniumServerUrl: String = "http://localhost:4444/wd/hub",
        private val driver: RemoteWebDriver = RemoteWebDriver(URL(seleniumServerUrl), DesiredCapabilities.chrome())
) : En {
    init {
        Given("メールアドレス{string}、パスワード{string}を入力する") { mailAddress: String, password: String ->
            driver.get("$frontServerHostname/signup")
            driver.findElementByClassName("e2e-mailaddress").sendKeys(mailAddress)
            driver.findElementByClassName("e2e-password").sendKeys(password)
        }

        When("Submitボタンを押下する") {
            // Write code here that turns the phrase above into concrete actions
            driver.findElementByClassName("e2e-form").submit()
        }

        Then("自分のユーザープロフィール画面に遷移する") {
            try {
                assertThat(driver.currentUrl).startsWith("$frontServerHostname/user")
            } finally {
                println("****finally*****")
                driver.quit()
            }
        }
    }
}