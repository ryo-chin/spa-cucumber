package com.hakiba.spacucumber.graphql.resolver

import com.coxautodev.graphql.tools.GraphQLQueryResolver
import org.springframework.stereotype.Component

/**
 * @author hakiba
 */
@Component
class AppInfoResolver : GraphQLQueryResolver {
    @NonAuth
    fun appInfo() : AppInfo {
        return AppInfo("Hakiba corp", "000-0000-0000")
    }
}

annotation class NonAuth

data class AppInfo(
        val createCompanyName: String,
        val phoneNumber: String
)
