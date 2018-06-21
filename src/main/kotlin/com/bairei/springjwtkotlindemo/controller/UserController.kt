package com.bairei.springjwtkotlindemo.controller

import com.bairei.springjwtkotlindemo.domain.auth.User
import com.bairei.springjwtkotlindemo.repository.UserRepository
import com.bairei.springjwtkotlindemo.service.RoleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
    fun signUp(@RequestBody user: User): ResponseEntity<*>{
        if(userRepository.findByUsername(user.username).isPresent) {
            return ResponseEntity("This user is already registered!", HttpStatus.BAD_REQUEST)
        }
        user.password = bCryptPasswordEncoder.encode(user.password)
        user.roles = arrayListOf(roleService.getUserRole())
        userRepository.save(user)

        return ResponseEntity.ok("User successfully registered, now you can log in using these credentials")
    }
}