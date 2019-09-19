package com.hakiba.spacucumber.spring

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

/**
 * @author hakiba
 */
@Configuration
class JooqConfig {

    @Bean
    fun dsl() : DSLContext {
        return DSL.using(dataSource(), SQLDialect.MYSQL)
    }

    @Bean
    fun dataSource() : DataSource {
        val dataSource = MysqlDataSource()
        dataSource.user = "root"
        dataSource.setPassword("")
        dataSource.setUrl("jdbc:mysql://localhost:3309/spa_cucumber_db")
        return dataSource
    }

    @Bean
    fun transactionManager(ds: DataSource) : PlatformTransactionManager {
        return DataSourceTransactionManager(ds)
    }
}
