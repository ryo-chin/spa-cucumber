package com.hakiba.spacucumber.db

import com.hakiba.spacucumber.jooq.Tables.USERS
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.conf.Settings
import org.jooq.impl.DSL
import org.jooq.impl.DSL.using
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.sql.Connection
import java.sql.DriverManager
import java.sql.Timestamp
import java.time.LocalDate


/**
 * @author hakiba
 */
class JooqRollbackTest {
    var userName = "root"
    var password = ""
    var url = "jdbc:mysql://localhost:3309/spa_cucumber_db"
    private lateinit var dsl: DSLContext
    private lateinit var con: Connection

    @Before
    fun setup() {
        con = DriverManager.getConnection(url, userName, password)
        con.autoCommit = false
        // jooq
        val settings = Settings()
        settings.isExecuteLogging = true
        settings.withRenderFormatted(true)
        settings.withRenderSchema(false)
        dsl = using(con, SQLDialect.MYSQL, settings)
    }

    @Test
    fun test_ユーザーを保存できること() {
        val before = count()

        dsl.insertInto(USERS)
                .set(USERS.FIRST_NAME, "Hakiba")
                .set(USERS.LAST_NAME, "BakiBaki")
                .set(USERS.BIRTH_DATE, Timestamp.valueOf(LocalDate.of(1991, 12, 3).atStartOfDay()))
                .set(USERS.EMAIL, "hakiba@bakibaki.com")
                .set(USERS.GENDER, "M")
                .set(USERS.PASSWORD, "password")
                .set(USERS.PHONE_NUMBER, "080-9999-9999")
                .execute()

        val after = count()
        println("Before: $before, After: $after")
    }

    private fun count() : Int {
        return dsl.selectCount().from(USERS).fetchOne(DSL.count())
    }

    @After
    fun cleanup() {
        con.rollback()
        println("After Clean up: ${count()}")
        con.close()
    }
}
