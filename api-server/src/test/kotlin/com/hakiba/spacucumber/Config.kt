package com.hakiba.spacucumber

import com.hakiba.spacucumber.page.SignUpPage
import com.hakiba.spacucumber.page.UserProfilePage
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

/**
 * @author hakiba
 */
val frontServerHostname: String = "http://frontserver:4200"
val seleniumServerUrl: String = "http://localhost:4444/wd/hub"
val schreeShotFolderPath: String = "tmp"

val browser = Browser(RemoteWebDriver(URL(seleniumServerUrl), ChromeOptions()))
val signUpPageUrl = "$frontServerHostname/signup"
val signUpPage = SignUpPage(signUpPageUrl, browser)
val userProfilePageUrl = "$frontServerHostname/user/\\d+"
val userProfilePage = UserProfilePage(userProfilePageUrl, browser)
