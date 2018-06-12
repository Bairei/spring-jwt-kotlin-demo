package com.bairei.springjwtkotlindemo.controller

import com.bairei.springjwtkotlindemo.domain.auth.Role
import com.bairei.springjwtkotlindemo.domain.auth.User
import com.bairei.springjwtkotlindemo.repository.UserRepository
import com.bairei.springjwtkotlindemo.service.RoleService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController(private val userRepository: UserRepository,
                     private val bCryptPasswordEncoder: BCryptPasswordEncoder,
                     private val roleService: RoleService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody user: User) {
        user.password = bCryptPasswordEncoder.encode(user.password)
        user.roles = arrayListOf(roleService.getAdminRole())
        userRepository.save(user)
    }
}