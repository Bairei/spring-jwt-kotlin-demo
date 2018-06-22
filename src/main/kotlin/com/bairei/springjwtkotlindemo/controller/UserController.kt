package com.bairei.springjwtkotlindemo.controller

import com.bairei.springjwtkotlindemo.domain.auth.User
import com.bairei.springjwtkotlindemo.dto.UserDto
import com.bairei.springjwtkotlindemo.dto.UserToUserDto
import com.bairei.springjwtkotlindemo.repository.UserRepository
import com.bairei.springjwtkotlindemo.service.RoleService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController(private val userRepository: UserRepository,
                     private val bCryptPasswordEncoder: BCryptPasswordEncoder,
                     private val roleService: RoleService, private val userToUserDto: UserToUserDto) {

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

    @GetMapping("/user/{username}")
    fun getUserInfo(@PathVariable username: String): UserDto {
        val user = userRepository.findByUsername(username)
        if (user.isPresent) {
            return user.map(userToUserDto::convert).get()
        } else throw UserNotFoundException("Couldn't get info for user $username!")
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun userNotFoundHandler(e: UserNotFoundException) = ResponseEntity(e.message, HttpStatus.NOT_FOUND)
}