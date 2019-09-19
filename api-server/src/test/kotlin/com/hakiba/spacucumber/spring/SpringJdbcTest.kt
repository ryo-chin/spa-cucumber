package com.hakiba.spacucumber.spring

import com.hakiba.spacucumber.jooq.Tables.USERS
import org.assertj.core.api.Assertions.assertThat
import org.jooq.DSLContext
import org.jooq.impl.DSL
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.sql.Timestamp
import java.time.LocalDate

/**
 * @author hakiba
 */
@RunWith(SpringRunner::class)
@JdbcTest
// [MEMO] can not use @Transactional, @Rollback with only @JdbcTest
//@Transactional
//@Rollback
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JooqConfig::class, TestConfig::class)
abstract class SpringJdbcTest

class JooqSpringTest : SpringJdbcTest() {
    @Autowired
    private lateinit var testComponent: TestComponent
    @Autowired
    private lateinit var dsl: DSLContext

    @Test
    fun test_xxx() {
        val before = dsl.selectCount().from(USERS).fetchOne(DSL.count())
        testComponent.insert()
        assertThat((dsl.selectCount().from(USERS).fetchOne(DSL.count()))).isEqualTo(before)
    }
}

@Configuration
class TestConfig {
    @Bean
    fun testComponent(dsl: DSLContext) : TestComponent = TestComponent(dsl)
}

// For Enhance
open class TestComponent(
        private val dsl: DSLContext
) {
    @Transactional
    open fun insert() {
        dsl.insertInto(USERS)
                .set(USERS.FIRST_NAME, "Hakiba")
                .set(USERS.LAST_NAME, "BakiBaki")
                .set(USERS.BIRTH_DATE, Timestamp.valueOf(LocalDate.of(1991, 12, 3).atStartOfDay()))
                .set(USERS.EMAIL, "hakiba@bakibaki.com")
                .set(USERS.GENDER, "M")
                .set(USERS.PASSWORD, "password")
                .set(USERS.PHONE_NUMBER, "080-9999-9999")
                .execute()
    }
}
