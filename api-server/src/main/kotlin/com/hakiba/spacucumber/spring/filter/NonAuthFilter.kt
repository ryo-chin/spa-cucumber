package com.hakiba.spacucumber.spring.filter

import com.coxautodev.graphql.tools.GraphQLResolver
import graphql.language.OperationDefinition
import graphql.parser.Parser
import graphql.servlet.GraphQLObjectMapper
import graphql.servlet.internal.GraphQLRequest
import org.apache.commons.io.IOUtils
import org.springframework.web.filter.OncePerRequestFilter
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets
import javax.servlet.FilterChain
import javax.servlet.ReadListener
import javax.servlet.ServletInputStream
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletRequestWrapper
import javax.servlet.http.HttpServletResponse


/**
 * @author hakiba
 */
class NonAuthFilter(
        private val resolvers: List<GraphQLResolver<*>>,
        private val graphQLObjectMapper: GraphQLObjectMapper
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val baos = ByteArrayOutputStream()
        val requestWrapper = BufferedServletRequestWrapper(request)
        IOUtils.copy(requestWrapper.inputStream.buffered(), baos)
        val reqBody = baos.toString(StandardCharsets.UTF_8)
        val requestInfo = """Filtering by NonAuthFilter
            request uri :${request.requestURI}
            request header :${request.headerNames.toList()}
            authorization header :${request.getHeader("authorization")}
            request params :${request.parameterMap.entries}
            request body :$reqBody 
            """
        logger.info(requestInfo)


        var gqlReqs: List<GraphQLRequest>
        if (isBatchedQuery(reqBody)) {
            gqlReqs = graphQLObjectMapper.readBatchedGraphQLRequest(requestWrapper.inputStream)
        } else {
            gqlReqs = listOf(graphQLObjectMapper.readGraphQLRequest(requestWrapper.inputStream))
        }
        logger.info("""gqls:
            |  operationName: ${gqlReqs.map { it.operationName }}
            |  query: ${gqlReqs.map { it.query }}
            |  variables: ${gqlReqs.map { it.variables }}
            |""")
        val parser = Parser()
        val docs = gqlReqs.map { parser.parseDocument(it.query) }
        docs.flatMap { it.definitions }
                .filterIsInstance<OperationDefinition>()
                .forEach { logger.info(it) }

        super.doFilter(requestWrapper, response, filterChain)
    }

    // return true if the first non whitespace character is the beginning of an array
    private fun isBatchedQuery(body: String): Boolean {
        return body.trim().startsWith("[")
    }
}

class BufferedServletInputStream(buffer: ByteArray) : ServletInputStream() {
    override fun isReady(): Boolean {
        return false
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun setReadListener(listener: ReadListener?) {

    }

    private val buffer: ByteArray
    private val inputStream: ByteArrayInputStream

    init {
        this.buffer = buffer
        this.inputStream = ByteArrayInputStream(buffer)
    }

    override fun available(): Int {
        return inputStream.available()
    }

    override fun read(): Int {
        return inputStream.read()
    }

    override fun read(b: ByteArray, off: Int, len: Int): Int {
        return inputStream.read(b, off, len)
    }
}

class BufferedServletRequestWrapper
constructor(request: HttpServletRequest) : HttpServletRequestWrapper(request) {

    private val buffer: ByteArray

    override fun getInputStream(): ServletInputStream {
        return BufferedServletInputStream(this.buffer)
    }

    init {
        val inputStream = request.inputStream
        val baos = ByteArrayOutputStream()
        val buff = ByteArray(1024)
        var read: Int = inputStream.read(buff)
        while (read > 0) {
            baos.write(buff, 0, read)
            read = inputStream.read(buff)
        }

        this.buffer = baos.toByteArray()
    }
}