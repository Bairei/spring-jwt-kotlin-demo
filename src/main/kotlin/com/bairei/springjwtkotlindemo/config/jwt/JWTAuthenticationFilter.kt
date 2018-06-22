package com.bairei.springjwtkotlindemo.config.jwt

import com.bairei.springjwtkotlindemo.config.SecurityConstants.EXPIRATION_DATE
import com.bairei.springjwtkotlindemo.config.SecurityConstants.HEADER_STRING
import com.bairei.springjwtkotlindemo.config.SecurityConstants.SECRET
import com.bairei.springjwtkotlindemo.config.SecurityConstants.TOKEN_PREFIX
import com.bairei.springjwtkotlindemo.domain.auth.User
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import kotlin.collections.ArrayList


class JWTAuthenticationFilter(
        private val authManager: AuthenticationManager,
        private val userDetailsService: UserDetailsService
): UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        try {
            val creds = ObjectMapper().readValue(request.inputStream, User::class.java)
            val user = userDetailsService.loadUserByUsername(creds.username)

            return authManager.authenticate(UsernamePasswordAuthenticationToken(creds.username,
                    creds.password, ArrayList()))
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse,
                                          chain: FilterChain, authResult: Authentication) {
        val token = Jwts.builder()
                .setSubject((authResult.principal as org.springframework.security.core.userdetails.User).username)
                .claim("roles", (authResult.principal as org.springframework.security.core.userdetails.User).authorities)
                .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_DATE))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact()
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token)
        }
}