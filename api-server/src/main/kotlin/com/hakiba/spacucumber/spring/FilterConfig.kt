package com.hakiba.spacucumber.spring

import com.coxautodev.graphql.tools.GraphQLResolver
import com.hakiba.spacucumber.spring.filter.AuthCheckFilter
import graphql.servlet.GraphQLObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author hakiba
 */
@Configuration
class FilterConfig {
    @Bean
    fun nonAuthFilter(
            resolvers: List<GraphQLResolver<*>>,
            graphQLObjectMapper: GraphQLObjectMapper) : AuthCheckFilter {
        return AuthCheckFilter(resolvers, graphQLObjectMapper)
    }
}