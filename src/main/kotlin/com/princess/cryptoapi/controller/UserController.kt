package com.princess.cryptoapi.controller

import com.princess.cryptoapi.dto.PortfolioDTO
import com.princess.cryptoapi.dto.UserDTO
import com.princess.cryptoapi.service.PortfolioService
import com.princess.cryptoapi.service.UserService
import jakarta.validation.Valid
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@Validated
@RestController
@RequestMapping("/users")
class UserController(
    private val service: UserService, private val portfolioService: PortfolioService
) {

    private val log = LoggerFactory.getLogger(this::class.java)

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@Valid @RequestBody details: UserDTO): UserDTO {
        log.info("Running POST /users method.")
        return service.create(details).also { log.info("User created.") }
    }

    @GetMapping("/{id}")
    fun find(@PathVariable("id") id: UUID): UserDTO {
        log.info("Running GET /users/{id} method.")
        return service.find(id).also { log.info("User fetched.") }
    }

    @GetMapping("/{id}/portfolio")
    fun findByUserId(@PathVariable("id") id: UUID): List<PortfolioDTO> {
        log.info("Running GET /users/{id}/portfolio method.")
        return portfolioService.findByUserId(id).also { log.info("Portfolios fetched.") }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: UUID, @Valid @RequestBody details: UserDTO): UserDTO {
        log.info("Running PUT /users/{id} method.")
        return service.update(id, details).also { log.info("User updated.") }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: UUID) {
        log.info("Running DELETE /users/{id} method.")
        return service.delete(id).also { log.info("User deleted.") }
    }
}