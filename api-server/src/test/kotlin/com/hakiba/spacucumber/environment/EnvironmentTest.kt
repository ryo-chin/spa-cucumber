package com.hakiba.spacucumber.environment

import org.junit.Before
import org.junit.Test
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

/**
 * @author hakiba
 */
class EnvironmentTest {
    lateinit var properties: Properties

    @Before
    fun setup() {
        properties = Properties()
        properties.load(Files.newBufferedReader(Paths.get("src/test/resources/application.properties")))

    }

    @Test
    fun test_db_properties() {
        println("db.userName: ${properties["db.userName"]}")
        println("db.password: ${properties["db.password"]}")
        println("db.url: ${properties["db.url"]}")
    }
}