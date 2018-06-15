package com.bairei.springjwtkotlindemo.config

import com.bairei.springjwtkotlindemo.config.SecurityConstants.SIGN_UP_URL
import com.bairei.springjwtkotlindemo.config.jwt.JWTAuthenticationFilter
import com.bairei.springjwtkotlindemo.config.jwt.JWTAuthorizationFilter
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@EnableWebSecurity
class WebSecurity(@Qualifier("myUserDetails") private val userDetailsService: UserDetailsService,
                  private val bCryptPasswordEncoder: BCryptPasswordEncoder): WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(JWTAuthenticationFilter(authenticationManager(), userDetailsService))
                .addFilter(JWTAuthorizationFilter(authenticationManager(), userDetailsService))
                // disabling session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val source = UrlBasedCorsConfigurationSource()
        val config = CorsConfiguration().apply {
            allowCredentials = true
            allowedOrigins = arrayListOf("http://localhost:8081", "http://localhost:8080", "http://localhost:4200")
            addExposedHeader(SecurityConstants.HEADER_STRING)
            allowedHeaders = arrayListOf("*")
            allowedMethods = arrayListOf("*")
        }
        source.registerCorsConfiguration("/**", config)
        return source
    }
}