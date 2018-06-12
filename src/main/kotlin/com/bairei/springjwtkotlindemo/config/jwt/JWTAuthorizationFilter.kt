package com.bairei.springjwtkotlindemo.config.jwt

import com.bairei.springjwtkotlindemo.config.SecurityConstants.HEADER_STRING
import com.bairei.springjwtkotlindemo.config.SecurityConstants.SECRET
import com.bairei.springjwtkotlindemo.config.SecurityConstants.TOKEN_PREFIX
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(authenticationManager: AuthenticationManager
                             ,private val userDetailsService: UserDetailsService
) : BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header = request.getHeader(HEADER_STRING)

        if(header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response)
            return
        }
        val authentication = getAuthentication(request)
        SecurityContextHolder.getContext().authentication = authentication
        chain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token = request.getHeader(HEADER_STRING)

        if (token != null) {
            val user = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .body
                    .subject
            val userObject = userDetailsService.loadUserByUsername(user)
            if (user != null) {
                return  UsernamePasswordAuthenticationToken(user, null, userObject.authorities)
            }
            return  null
        }
        return null
    }
}