package com.hakiba.spacucumber

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import org.junit.runner.RunWith

/**
 * @author hakiba
 */
@RunWith(Cucumber::class)
@CucumberOptions(
        features = ["src/test/resources/features"],
        tags = ["not @developing"]
)
class RunCucumber

@RunWith(Cucumber::class)
@CucumberOptions(
        features = ["src/test/resources/features"],
        tags = ["@now"]
)
class RunCucumberNow
