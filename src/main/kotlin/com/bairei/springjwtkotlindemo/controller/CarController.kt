package com.bairei.springjwtkotlindemo.controller

import com.bairei.springjwtkotlindemo.domain.Car
import com.bairei.springjwtkotlindemo.repository.CarRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.persistence.EntityNotFoundException

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
    fun editCar(@PathVariable id: Long, @RequestBody car: Car): Car {
        car.id = id
        return carRepository.save(car)
    }

    @DeleteMapping("/{id}")
    fun deleteCar(@PathVariable id: Long) = carRepository.deleteById(id)


    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Entity not found")
    @ExceptionHandler(EntityNotFoundException::class)
    fun notFoundExceptionHandler() = ResponseEntity("The queried entity was not found", HttpStatus.NOT_FOUND)

}