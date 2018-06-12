package com.bairei.springjwtkotlindemo.repository

import com.bairei.springjwtkotlindemo.domain.Car
import org.springframework.data.jpa.repository.JpaRepository

interface CarRepository: JpaRepository<Car, Long>