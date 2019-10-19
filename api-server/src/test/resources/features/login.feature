Feature: ログイン

  @now
  Scenario: ログインができること
    Given "Hakiba"というユーザーが登録されている
    And ログイン画面を開いている
    When メールアドレス欄に"hakiba@gm.com"、パスワード欄に"SwP32hB2LEXc"と入力する
    And ログインボタンを押す
    Then ユーザープロフィール画面で "ようこそ、Hakibaさん" が表示される
