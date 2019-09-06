package com.hakiba.spacucumber.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.hakiba.spacucumber.controller.UserController.Companion.userStore
import com.hakiba.spacucumber.controller.UserDto
import org.springframework.stereotype.Component

/**
 * @author hakiba
 */
@Component
class UserQueryResolver : GraphQLQueryResolver {
    fun user(id: String): UserDto? {
        return userStore
    }
}

@Component
class UserMutationResolver: GraphQLMutationResolver {
    fun signup(mailAddress: String,  password: String) : UserDto {
        val user = UserDto(id = 1, mailAddress = mailAddress)
        userStore = user
        return user
    }
}