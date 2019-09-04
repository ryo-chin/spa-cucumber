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
        tags = ["not @developing"],
        glue = ["com.hakiba.spacucumber.steps"]
)
class RunCucumber

@RunWith(Cucumber::class)
@CucumberOptions(
        features = ["src/test/resources/features"],
        tags = ["@now"],
        glue = ["com.hakiba.spacucumber.steps"]
)
class RunCucumberNow
