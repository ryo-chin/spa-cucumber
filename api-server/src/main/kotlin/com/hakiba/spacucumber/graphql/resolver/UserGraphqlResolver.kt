package com.hakiba.spacucumber.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.hakiba.spacucumber.controller.UserController.Companion.userStore
import com.hakiba.spacucumber.controller.UserDto
import com.hakiba.spacucumber.prepareLogger
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.jwt.JwtDecoders
import org.springframework.security.oauth2.jwt.NimbusJwtDecoderJwkSupport
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest

/**
 * @author hakiba
 */
@Component
class UserQueryResolver(
        private val httpRequest: HttpServletRequest,
        @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
        private val issuer: String? = null
) : GraphQLQueryResolver {

    val logger = prepareLogger()

    fun user(id: String): UserDto? {
        return userStore
    }

    fun me(): UserDto? {
        val jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer) as NimbusJwtDecoderJwkSupport
        val token = httpRequest.getHeader("authorization").replace("Bearer ", "")
        val jwt = jwtDecoder.decode(token)
        logger.info("token: $token")
        logger.info("jwt: $jwt")
        logger.info("claims: ${jwt.claims}")
        val userId = jwt.claims["sub"] as String
        val email = jwt.claims["email"] as String
        return UserDto(userId, email)
    }
}

@Component
class UserMutationResolver: GraphQLMutationResolver {
    fun signup(mailAddress: String,  password: String) : UserDto {
        val user = UserDto(id = "1", mailAddress = mailAddress)
        userStore = user
        return user
    }
}