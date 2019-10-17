package com.hakiba.spacucumber.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

/**
 * @author hakiba
 */
@Component
class AppInfoResolver : GraphQLQueryResolver {
    @AuthRequired
    fun appInfo() : AppInfo {
        return AppInfo("Hakiba corp", "000-0000-0000")
    }
}

annotation class AuthRequired

data class AppInfo(
        val createCompanyName: String,
        val phoneNumber: String
)
