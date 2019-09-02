package com.hakiba.spacucumber.steps

import com.hakiba.spacucumber.currentUrl
import com.hakiba.spacucumber.frontServerHost
import com.hakiba.spacucumber.signUpPO
import io.cucumber.java8.En
import org.assertj.core.api.Assertions.assertThat


/**
 * @author hakiba
 */
class SignupSteps : En {
    init {
        Given("メールアドレス{string}、パスワード{string}を入力する") { mailAddress: String, password: String ->
            signUpPO.visit("$frontServerHost/signup")
            signUpPO.input("e2e-mailaddress", mailAddress)
            signUpPO.input("e2e-password", password)
        }

        When("Submitボタンを押下する") {
            signUpPO.submit("e2e-form")
        }

        Then("自分のユーザープロフィール画面に遷移する") {
            assertThat(currentUrl()).startsWith("$frontServerHost/user")
        }
    }
}
