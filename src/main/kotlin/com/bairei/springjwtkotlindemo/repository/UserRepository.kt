package com.bairei.springjwtkotlindemo.repository

import com.bairei.springjwtkotlindemo.domain.auth.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository: JpaRepository<User, Long> {
    fun findByUsername(username: String): Optional<User>

}