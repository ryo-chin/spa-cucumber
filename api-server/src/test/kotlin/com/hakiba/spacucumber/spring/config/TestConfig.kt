package com.hakiba.spacucumber.spring.config

import com.hakiba.spacucumber.spring.TestComponent
import graphql.servlet.GraphQLObjectMapper
import org.jooq.DSLContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author hakiba
 */
@Configuration
class TestConfig {
    @Bean
    fun testComponent(dsl: DSLContext) : TestComponent = TestComponent(dsl)

    @Bean
    fun graphqlObjectMapper() : GraphQLObjectMapper = GraphQLObjectMapper.newBuilder().build()
}