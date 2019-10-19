package com.hakiba.spacucumber.steps

import com.hakiba.spacucumber.base.BaseStep
import com.hakiba.spacucumber.base.WebPageHolder
import com.hakiba.spacucumber.page.LoginPage
import com.hakiba.spacucumber.page.MyPage
import org.assertj.core.api.Assertions.assertThat

/**
 * @author hakiba
 */
class LoginSteps : BaseStep() {
    private val loginPage = WebPageHolder.retrieve(LoginPage::class)

    init {
        Given("\"(.*)\"というユーザーが登録されている") { userName: String ->
            // Auth0に登録する?
        }

        Given("ログイン画面を開いている") {
            loginPage.visit()
        }

        When("メールアドレス欄に\"(.*)\"、パスワード欄に\"(.*)\"と入力する") { email: String, password: String ->
            loginPage.inputEmailAndPassword(email, password)
        }

        When("ログインボタンを押す") {
            loginPage.clickLoginButton()
        }

        Then("ユーザープロフィール画面で \"ようこそ、(.*)さん\" が表示される") { userName: String ->
            val myPage = WebPageHolder.retrieve(MyPage::class)
            assertThat(myPage.isOpened()).isTrue()
            assertThat(myPage.isDisplayedName(userName)).isTrue()
        }
    }
}
