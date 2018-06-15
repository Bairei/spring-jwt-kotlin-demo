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
        if (carRepository.count() == 0L) {
            println("### Creating car objects ###")
            val cars = arrayListOf(
                    Car(make = "Subaru", model = "Impreza", horsePower = 200, yearOfProduction = 2004),
                    Car(make = "Dodge", model = "Challenger", horsePower = 300, yearOfProduction = 2009),
                    Car(make = "Mitsubishi", model = "Lancer", horsePower = 330, yearOfProduction = 2011),
                    Car(make = "Ford", model = "Focus RS", horsePower = 300, yearOfProduction = 2015),
                    Car(make = "Nissan", model = "370Z", horsePower = 320, yearOfProduction = 2013),
                    Car(make = "Dodge", model = "Charger", horsePower = 230, yearOfProduction = 1968),
                    Car(make = "Plymouth", model = "Barracuda", horsePower = 300, yearOfProduction = 1971),
                    Car(make = "Honda", model = "S2000", horsePower = 210, yearOfProduction = 2002),
                    Car(make = "Hyundai", model = "Tiburon", horsePower = 200, yearOfProduction = 2002),
                    Car(make = "Porsche", model = "Carrera GT", horsePower = 400, yearOfProduction = 2006),
                    Car(make = "Aston Martin", model = "DB9", horsePower = 320, yearOfProduction = 2007)
            )

            carRepository.saveAll(cars)
            println("Current car count: ${carRepository.count()}")
        }

        if (roleRepository.count() == 0L) {
            println("### Creating role objects ###")

            val roles = arrayListOf(
                    Role(roleName = "ROLE_ADMIN"),
                    Role(roleName = "ROLE_USER")
            )

            roleRepository.saveAll(roles)
            println("Current role count: ${roleRepository.count()}")
        }
    }
}