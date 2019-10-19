package com.hakiba.spacucumber

import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.chrome.ChromeOptions

/**
 * @author hakiba
 */
const val screenShotFolderPath: String = "tmp"

// if use docker selenium
// const val frontServerHostName: String = "http://frontserver:4200"
// const val seleniumServerUrl: String = "http://localhost:4444/wd/hub"
// val browser = Browser(RemoteWebDriver(URL(seleniumServerUrl), ChromeOptions()))

// if use Headless Chrome
// you need install by "brew tap homebrew/cask && brew cask install chromedriver"
const val frontServerHostName: String = "http://localhost:4200"
val browser = Browser(ChromeDriver(ChromeOptions().setHeadless(true)))
