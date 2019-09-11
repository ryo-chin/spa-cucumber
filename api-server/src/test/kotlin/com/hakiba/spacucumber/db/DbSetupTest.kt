package com.hakiba.spacucumber.db

import com.ninja_squad.dbsetup.destination.Destination
import com.ninja_squad.dbsetup.destination.DriverManagerDestination
import com.ninja_squad.dbsetup_kotlin.dbSetup
import com.ninja_squad.dbsetup_kotlin.mappedValues
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * @author hakiba
 */
class DbSetupTest {
    var userName = "root"
    var password = ""
    var url = "jdbc:mysql://localhost:3309/spa_cucumber_db"
    private lateinit var dest : Destination

    @Before
    fun setup() {
        dest = DriverManagerDestination("$url?autoReconnect=true&useSSL=false", userName, password)
    }

    @Test
    fun xxx() {
        dbSetup(dest) {
            insertInto("users") {
                mappedValues(
                        "first_name" to "Hakiba",
                        "last_name" to "BakiBaki",
                        "birth_date" to "1991-12-03 00:00:00",
                        "email" to "hakiba@bakibaki.com",
                        "gender" to "M",
                        "password" to "password",
                        "phone_number" to "080-9999-9999"
                        )
            }
        }.launch()
    }
}