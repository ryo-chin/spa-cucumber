package com.hakiba.spacucumber.spring

import com.coxautodev.graphql.tools.GraphQLResolver
import com.hakiba.spacucumber.spring.filter.NonAuthFilter
import graphql.servlet.GraphQLObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * @author hakiba
 */
@Configuration
class FilterConfig {
//    @Bean
//    fun graphQLFilterConfig(nonAuthFilter: NonAuthFilter) : FilterRegistrationBean<NonAuthFilter> {
//        return FilterRegistrationBean(nonAuthFilter).also {
//            it.order = Ordered.HIGHEST_PRECEDENCE
//            it.urlPatterns = listOf("/graphql/*")
//        }
//    }

    @Bean
    fun nonAuthFilter(
            resolvers: List<GraphQLResolver<*>>,
            graphQLObjectMapper: GraphQLObjectMapper) : NonAuthFilter {
//        resolvers
//                .flatMap { it.javaClass.declaredMethods.toList() }
//                .filter { it.annotations.contains(NonAuth::class.java) }
        return NonAuthFilter(resolvers, graphQLObjectMapper)
    }
}