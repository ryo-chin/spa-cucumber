package com.hakiba.spacucumber.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.hakiba.spacucumber.controller.UserDto
import org.springframework.stereotype.Component

/**
 * @author hakiba
 */
@Component
class UserQueryGraphqlResolver : GraphQLQueryResolver {
    companion object {
        var userStore: UserDto? = null
    }

    fun user(): UserDto? {
        return userStore
    }
}
