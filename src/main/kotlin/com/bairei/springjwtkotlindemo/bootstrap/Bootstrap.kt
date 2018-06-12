package com.bairei.springjwtkotlindemo.bootstrap

import com.bairei.springjwtkotlindemo.domain.Car
import com.bairei.springjwtkotlindemo.domain.auth.Role
import com.bairei.springjwtkotlindemo.repository.CarRepository
import com.bairei.springjwtkotlindemo.repository.RoleRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class Bootstrap(private val carRepository: CarRepository,
                private val roleRepository: RoleRepository): CommandLineRunner {

    override fun run(vararg args: String?) {
        val cars = arrayListOf(
                Car(make="Subaru", model = "Impreza", horsePower = 200, yearOfProduction = 2004),
                Car(make = "Dodge", model = "Challenger", horsePower = 300, yearOfProduction = 2009)
        )

        carRepository.saveAll(cars)

        val roles = arrayListOf(
                Role(roleName = "ROLE_ADMIN"),
                Role(roleName = "ROLE_USER")
        )

        roleRepository.saveAll(roles)
    }
}