package com.hakiba.spacucumber.steps

import com.hakiba.spacucumber.base.BaseStep
import com.hakiba.spacucumber.signUpPage
import org.assertj.core.api.Assertions.assertThat


/**
 * @author hakiba
 */
class SignupSteps : BaseStep() {
    init {
        Given("メールアドレス{string}、パスワード{string}を入力する") { mailAddress: String, password: String ->
            signUpPage.visit()
            signUpPage.input(mailAddress = mailAddress, password = password)
        }

        When("Submitボタンを押下する") {
            signUpPage.submit()
        }

        Then("自分のユーザープロフィール画面に遷移する") {
            assertThat(signUpPage.navigateToUserProfilePage()?.isOpened()).isTrue()
        }
    }
}
