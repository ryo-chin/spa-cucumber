package com.hakiba.spacucumber.steps

import com.hakiba.spacucumber.base.BaseStep
import com.hakiba.spacucumber.base.WebPageHolder
import com.hakiba.spacucumber.page.SignUpPage
import com.hakiba.spacucumber.page.UserProfilePage
import org.assertj.core.api.Assertions.assertThat


/**
 * @author hakiba
 */
class SignUpSteps : BaseStep() {
    private val signUpPage = WebPageHolder.retrieve(SignUpPage::class)
    init {
        Given("メールアドレス{string}、パスワード{string}を入力する") { mailAddress: String, password: String ->
            signUpPage.visit() // TODO: separate another step
            signUpPage.input(mailAddress = mailAddress, password = password)
        }

        When("Submitボタンを押下する") {
            signUpPage.submit()
        }

        Then("自分のユーザープロフィール画面に遷移する") {
            assertThat(WebPageHolder.retrieve(UserProfilePage::class).isOpened()).isTrue()
        }
    }
}
