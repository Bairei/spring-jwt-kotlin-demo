package com.bairei.springjwtkotlindemo.controller

import com.bairei.springjwtkotlindemo.domain.Car
import com.bairei.springjwtkotlindemo.repository.CarRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/car")
class CarController(private val carRepository: CarRepository) {

    @PostMapping
    fun addCar(@RequestBody car: Car) = carRepository.save(car)

    @GetMapping
    fun getCars() = carRepository.findAll()

    @GetMapping("/{id}")
    fun getCar(@PathVariable id: Long) = carRepository.getOne(id)

    @PutMapping("/{id}")
    fun editCar(@PathVariable id: Long, @RequestBody car: Car): Car? = carRepository.findById(id).map {
        it.yearOfProduction = if (car.yearOfProduction == 0) it.yearOfProduction else car.yearOfProduction
        it.make = if (car.make.isEmpty()) it.make else car.make
        it.model = if (car.model.isEmpty()) it.model else car.model
        it.horsePower = if (car.horsePower == 0) it.horsePower else car.horsePower
        return@map it
    }.orElse(null)


}