package com.bairei.springjwtkotlindemo.domain.auth

import javax.persistence.*

@Entity
data class User (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var username: String = "",
//    @JsonIgnore
    var password: String = "",
    var firstName: String = "",
    var lastName: String = "",
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = arrayOf(
            JoinColumn(name = "user_role", referencedColumnName = "id")),
            inverseJoinColumns = arrayOf(JoinColumn(name = "role_id", referencedColumnName = "id")))
    var roles: List<Role> = ArrayList()
)