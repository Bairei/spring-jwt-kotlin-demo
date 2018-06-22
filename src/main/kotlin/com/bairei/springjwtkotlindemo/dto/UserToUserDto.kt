package com.bairei.springjwtkotlindemo.dto

import com.bairei.springjwtkotlindemo.domain.auth.User
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class UserToUserDto: Converter<User, UserDto> {
    override fun convert(user: User): UserDto = UserDto(username = user.username,
            firstName = user.firstName, lastName = user.lastName)
}