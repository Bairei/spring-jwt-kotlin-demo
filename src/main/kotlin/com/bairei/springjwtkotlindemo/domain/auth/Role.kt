package com.bairei.springjwtkotlindemo.domain.auth

import org.springframework.security.core.GrantedAuthority
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Role(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        var roleName: String = ""
): GrantedAuthority {
        override fun getAuthority(): String = roleName
}