package com.hakiba.spacucumber.spring

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2Error
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult
import org.springframework.security.oauth2.jwt.*
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

/**
 * @author hakiba
 */
@Configuration
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Value("\${auth0.audience}")
    private val audience: String? = null

    @Value("\${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private val issuer: String? = null

    @Value("\${spring.security.allowedOriginUrl}")
    private val allowedOriginUrl: String? = null

    // Auth0 + SpringSecurity5 ref: https://auth0.com/docs/quickstart/backend/java-spring-security5/01-authorization
    @Bean
    fun jwtDecoder(): JwtDecoder {
        val jwtDecoder = JwtDecoders.fromOidcIssuerLocation(issuer!!) as NimbusJwtDecoderJwkSupport
        val audienceValidator = AudienceValidator(audience)
        val withIssuer = JwtValidators.createDefaultWithIssuer(issuer)
        val withAudience = DelegatingOAuth2TokenValidator(withIssuer, audienceValidator)
        jwtDecoder.setJwtValidator(withAudience)
        return jwtDecoder
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        // Auth0 + SpringSecurity5 ref: https://auth0.com/docs/quickstart/backend/java-spring-security5/01-authorization
        http.oauth2ResourceServer().jwt()
        http.authorizeRequests()
                .antMatchers("/graphql").authenticated()
        // no needs session since use token auth
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        http.cors().configurationSource(corsConfigurationSource())
    }

    private fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL)
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL)
        corsConfiguration.addAllowedOrigin(allowedOriginUrl!!)

        val corsConfigurationSource = UrlBasedCorsConfigurationSource()
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration)
        return corsConfigurationSource
    }
}

// Auth0 + SpringSecurity5 ref: https://auth0.com/docs/quickstart/backend/java-spring-security5/01-authorization
class AudienceValidator(audience: String?) : OAuth2TokenValidator<Jwt> {
    companion object {
        private var audience: String? = null
    }

    init {
        AudienceValidator.audience = audience
    }

    override fun validate(jwt: Jwt): OAuth2TokenValidatorResult {
        val error = OAuth2Error("invalid_token", "The required audience is missing", null)

        if (jwt.audience.contains(audience)) {
            return OAuth2TokenValidatorResult.success()
        }

        return OAuth2TokenValidatorResult.failure(error)
    }
}