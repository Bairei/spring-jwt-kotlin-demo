package com.bairei.springjwtkotlindemo.service

import com.bairei.springjwtkotlindemo.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class MyUserDetails (private val userRepository: UserRepository): UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)

        if (!user.isPresent) {
            throw UsernameNotFoundException("User $username was not found!")
        }

        val userObject = user.get()
        return  org.springframework.security.core.userdetails.User(userObject.username,
                userObject.password, ArrayList())
    }
}