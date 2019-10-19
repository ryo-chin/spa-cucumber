package com.hakiba.spacucumber.spring.filter

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.coxautodev.graphql.tools.GraphQLQueryResolver
import com.coxautodev.graphql.tools.GraphQLResolver
import com.hakiba.spacucumber.graphql.resolver.AuthRequired
import graphql.language.Field
import graphql.language.OperationDefinition
import graphql.parser.Parser
import graphql.servlet.GraphQLObjectMapper
import graphql.servlet.internal.GraphQLRequest
import org.apache.commons.io.IOUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * @author hakiba
 */
class AuthCheckFilter(
        resolvers: List<GraphQLResolver<*>>,
        private val graphQLObjectMapper: GraphQLObjectMapper
) : OncePerRequestFilter() {
    private val queryResolverNames : List<String>
    private val mutationResolverNames : List<String>

    init {
        val queryResolvers = resolvers.filterIsInstance<GraphQLQueryResolver>()
        val mutationResolvers = resolvers.filterIsInstance<GraphQLMutationResolver>()
        this.queryResolverNames = queryResolvers
                .flatMap { it::class.java.methods.toList() }
                .filter { it.isAnnotationPresent(AuthRequired::class.java) }
                .map { it.name }
        this.mutationResolverNames = mutationResolvers
                .flatMap { it::class.java.methods.toList() }
                .filter { it.isAnnotationPresent(AuthRequired::class.java) }
                .map { it.name }
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return "/graphql" != request.requestURI
    }

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val requestWrapper = BufferedServletRequestWrapper(request)
        if (needsAuthorize(requestWrapper)) {
            super.doFilter(requestWrapper, response, filterChain)
            return
        }
        requestWrapper.getRequestDispatcher(requestWrapper.servletPath).forward(requestWrapper, response)
    }

    fun needsAuthorize(request: BufferedServletRequestWrapper): Boolean {
        val baos = ByteArrayOutputStream()
        IOUtils.copy(request.inputStream.buffered(), baos)
        val reqBody = baos.toString(StandardCharsets.UTF_8)

        // BatchRequestであるかの判定 ref: AbstractGraphQLHttpServlet#isBatchedQuery
        val gqlReqs: List<GraphQLRequest>
        gqlReqs = if (isBatchedQuery(reqBody)) {
            graphQLObjectMapper.readBatchedGraphQLRequest(request.inputStream)
        } else {
            listOf(graphQLObjectMapper.readGraphQLRequest(request.inputStream))
        }

        // Operationをparseする
        val parser = Parser()
        val docs = gqlReqs.map { parser.parseDocument(it.query) }
        val operations = docs.flatMap { it.definitions }.filterIsInstance<OperationDefinition>()
        val queryOperationNames = extractOperation(operations, OperationDefinition.Operation.QUERY)
        val mutationOperationNames = extractOperation(operations, OperationDefinition.Operation.MUTATION)

        // 認証不要なRequestのみであるかをチェック
        val needAuthQueryRequest = queryOperationNames.isNotEmpty() && queryOperationNames.asSequence().filter { queryResolverNames.contains(it) }.toList().isNotEmpty()
        val needAuthMutationRequest = mutationOperationNames.isNotEmpty() && mutationOperationNames.asSequence().filter { mutationResolverNames.contains(it) }.toList().isNotEmpty()
        return needAuthQueryRequest || needAuthMutationRequest
    }

    private fun extractOperation(operations: List<OperationDefinition>, type: OperationDefinition.Operation) =
            operations.asSequence()
                    .filter { it.operation == type }
                    .map { convertToFields(it) }
                    .flatten()
                    .map { it.name }
                    .toList()

    private fun convertToFields(operation: OperationDefinition) =
            operation.selectionSet.selections.asSequence()
                    .filterIsInstance<Field>()

    // return true if the first non whitespace character is the beginning of an array
    private fun isBatchedQuery(body: String): Boolean {
        return body.trim().startsWith("[")
    }
}
