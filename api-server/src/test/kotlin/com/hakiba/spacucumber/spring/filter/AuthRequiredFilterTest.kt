package com.hakiba.spacucumber.spring.filter

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.hakiba.spacucumber.graphql.resolver.AuthRequired
import com.hakiba.spacucumber.spring.SpringTestBase
import graphql.servlet.GraphQLObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.web.context.WebApplicationContext

/**
 * @author hakiba
 */
class AuthCheckFilterTest : SpringTestBase() {
    @Autowired
    lateinit var webApplicationContext: WebApplicationContext
    val filter = AuthCheckFilter(
            listOf(AuthTestQueryResolver(), AuthTestMutationResolver()),
            GraphQLObjectMapper.newBuilder().build()!!)

    @Test
    fun test_needsAuthorize_認証が必要なQueryの場合true() {
        // Arrange
        val request = mockHttpServletRequest("""{"query":"query AuthTestQuery { authQuery }"}""")

        // Act & Assert
        assertThat(filter.needsAuthorize(BufferedServletRequestWrapper(request))).isTrue()
    }

    @Test
    fun test_needsAuthorize_認証が不要なQueryの場合false() {
        // Arrange
        val request = mockHttpServletRequest("""{"query":"query AuthTestQuery { nonAuthQuery }"}""")

        // Act & Assert
        assertThat(filter.needsAuthorize(BufferedServletRequestWrapper(request))).isFalse()
    }

    @Test
    fun test_needsAuthorize_認証の要否どちらも含むQueryの場合true() {
        // Arrange
        val request = mockHttpServletRequest("""{"query":"query AuthTestQuery { nonAuthQuery authQuery }"}""")

        // Act & Assert
        assertThat(filter.needsAuthorize(BufferedServletRequestWrapper(request))).isTrue()
    }

    @Test
    fun test_needsAuthorize_認証が必要なMutationQueryの場合true() {
        // Arrange
        val request = mockHttpServletRequest("""{"query":"mutation AuthTestMutation { authMutation }"}""")

        // Act & Assert
        assertThat(filter.needsAuthorize(BufferedServletRequestWrapper(request))).isTrue()
    }

    @Test
    fun test_needsAuthorize_認証が不要なMutationQueryの場合false() {
        // Arrange
        val request = mockHttpServletRequest("""{"query":"mutation AuthTestMutation { nonAuthMutation }"}""")

        // Act & Assert
        assertThat(filter.needsAuthorize(BufferedServletRequestWrapper(request))).isFalse()
    }

    @Test
    fun test_needsAuthorize_認証の要否どちらも含むMutationQueryの場合true() {
        // Arrange
        val request = mockHttpServletRequest("""{"query":"mutation AuthTestMutation { nonAuthMutation authMutation }"}""")

        // Act & Assert
        assertThat(filter.needsAuthorize(BufferedServletRequestWrapper(request))).isTrue()
    }

    @Test
    fun test_needsAuthorize_fieldを持っている場合() {
        // Arrange
        val request = mockHttpServletRequest("""{"query":"query AuthTest { multiAuthQuery{ id name } }"}""")

        // Act & Assert
        assertThat(filter.needsAuthorize(BufferedServletRequestWrapper(request))).isFalse()
    }

    private fun mockHttpServletRequest(body: String): MockHttpServletRequest {
        return MockMvcRequestBuilders.post("graphql")
                .content(body)
                .buildRequest(webApplicationContext.servletContext!!)
    }
}

class AuthTestQueryResolver : GraphQLQueryResolver {
    @AuthRequired
    fun authQuery(): String {
        return "authQuery"
    }

    fun nonAuthQuery(): String {
        return "nonAuthQuery"
    }

    fun multiAuthQuery(): TestData {
        return TestData(1, "test")
    }

    data class TestData(
            val id: Int,
            val name: String

    )
}


class AuthTestMutationResolver : GraphQLMutationResolver {
    @AuthRequired
    fun authMutation(): String {
        return "authMutation"
    }

    fun nonMutation(): String {
        return "nonMutation"
    }
}