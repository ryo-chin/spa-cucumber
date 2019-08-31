package com.hakiba.spacucumber.steps

import io.cucumber.java8.En

/**
 * @author hakiba
 */
class SignupSteps : En {
    init {
        Given("メールアドレス{string}、パスワード{string}を入力する") { string: String, string2: String ->
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
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