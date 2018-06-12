package com.bairei.springjwtkotlindemo.service

import com.bairei.springjwtkotlindemo.domain.auth.Role
import com.bairei.springjwtkotlindemo.repository.RoleRepository
import org.springframework.stereotype.Service

@Service
class RoleService(private val roleRepository: RoleRepository) {

    fun getUserRole(): Role = roleRepository.findRoleByRoleName("ROLE_USER")

    fun getAdminRole(): Role = roleRepository.findRoleByRoleName("ROLE_ADMIN")
}