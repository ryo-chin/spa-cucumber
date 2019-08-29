package com.hakiba.spacucumber.steps

import io.cucumber.java8.En

/**
 * refs1. Github cucumber/cucumber-jvm
 *  - https://github.com/cucumber/cucumber-jvm/blob/master/kotlin-java8/src/test/kotlin/io/cucumber/kotlin/LambdaStepdefs.kt
 * refs2. Medium Kukumber — Getting started with Cucumber in Kotlin
 *  - https://medium.com/@mlvandijk/kukumber-getting-started-with-cucumber-in-kotlin-e55112e7309b
 * @author hakiba
 */
class HelloSteps : En {
    init {
        Given("I have {int} cukes in my belly") { int1: Int ->
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
        }

        Then("I really have {int} cukes in my belly") { int1: Int ->
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
        }

        Given("something that isn't defined") {
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
        }

        Given("A statement with a simple match") {
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
        }

        Given("A statement with a scoped argument") {
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
        }

        Given("A statement with a body expression") {
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
        }

        Given("I will give you {int} and {int} and three and {int}") { int1: Int, int2: Int, int3: Int ->
            // Write code here that turns the phrase above into concrete actions
            throw cucumber.api.PendingException()
        }

        Given("this data table:") { dataTable: io.cucumber.datatable.DataTable ->
            // Write code here that turns the phrase above into concrete actions
            // For automatic transformation, change DataTable to one of
            // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
            // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
            // Double, Byte, Short, Long, BigInteger or BigDecimal.
            //
            // For other transformations you can register a DataTableType.
            throw cucumber.api.PendingException()
        }
    }
}