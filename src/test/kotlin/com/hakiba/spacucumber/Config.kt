package com.hakiba.spacucumber

import com.hakiba.spacucumber.page.SignUpPage
import com.hakiba.spacucumber.page.UserProfilePage
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver
import java.net.URL

/**
 * @author hakiba
 */
val frontServerHostname: String = "http://frontserver:4200"
val seleniumServerUrl: String = "http://localhost:4444/wd/hub"
val schreeShotFolderPath: String = "tmp"

private val driver: RemoteWebDriver = RemoteWebDriver(URL(seleniumServerUrl), DesiredCapabilities.chrome())

val webDriverUtil = WebDriverUtil(driver)
val signUpPage = SignUpPage("$frontServerHostname/signup", driver)
val userProfilePage = UserProfilePage("$frontServerHostname/signup", driver)
