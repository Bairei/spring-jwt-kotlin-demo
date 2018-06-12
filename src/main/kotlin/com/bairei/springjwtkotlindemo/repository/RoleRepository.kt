package com.bairei.springjwtkotlindemo.repository

import com.bairei.springjwtkotlindemo.domain.auth.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, Long> {
    fun findRoleByRoleName(name: String): Role
}