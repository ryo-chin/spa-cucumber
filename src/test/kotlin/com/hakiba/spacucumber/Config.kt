package com.hakiba.spacucumber

val frontServerHost: String = getEnvOrProp("FRONT_SERVER_HOST", "http://localhost:4200")
val seleniumServerUrl: String = getEnvOrProp("SELENIUM_SERVER_URL", "http://localhost:4444/wd/hub")

fun getEnvOrProp(name: String, default: String): String = System.getenv(name) ?: System.getProperty(name) ?: default
