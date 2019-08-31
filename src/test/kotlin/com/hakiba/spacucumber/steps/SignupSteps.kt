package com.hakiba.spacucumber.steps

import io.cucumber.java8.En
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL


/**
 * @author hakiba
 */
class SignupSteps(
        private val FRONT_SERVER_HOSTNAME: String = "http://frontserver:4200",
        private val SELENIUM_SERVER_URL: String = "http://localhost:4444/wd/hub"
) : En {
    init {
        Given("メールアドレス{string}、パスワード{string}を入力する") { string: String, string2: String ->
            val driver = RemoteWebDriver(URL(SELENIUM_SERVER_URL), DesiredCapabilities.chrome())
            driver.get("$FRONT_SERVER_HOSTNAME/signup")
        }

        When("Submitボタンを押下する") {
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
        }

        Then("自分のユーザープロフィール画面に遷移する") {
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
        }
    }
}