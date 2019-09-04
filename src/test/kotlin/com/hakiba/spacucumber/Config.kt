package com.hakiba.spacucumber

import com.hakiba.spacucumber.page.SignUpPage
import org.openqa.selenium.chrome.ChromeOptions
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

/**
 * @author hakiba
 */
val frontServerHostname: String = "http://frontserver:4200"
val seleniumServerUrl: String = "http://localhost:4444/wd/hub"
val schreeShotFolderPath: String = "tmp"

private val driver: RemoteWebDriver = RemoteWebDriver(URL(seleniumServerUrl), ChromeOptions())

val browser = Browser(driver)
val signUpPageUrl = "$frontServerHostname/signup"
val signUpPage = SignUpPage(signUpPageUrl, driver)
val userProfilePageUrl = "$frontServerHostname/user/\\d+"
