 Feature: 会員登録

   @developing
   @now
   Scenario: 会員登録を行うことができる
     Given メールアドレス"hakiba@bakibaki.com"、パスワード"password"を入力する
     When Submitボタンを押下する
     Then 自分のユーザープロフィール画面に遷移する