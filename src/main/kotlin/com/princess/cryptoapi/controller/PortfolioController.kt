package com.princess.cryptoapi.controller

import com.princess.cryptoapi.dto.PortfolioDTO
import com.princess.cryptoapi.service.PortfolioService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@Validated
@RequestMapping("/portfolio")
class PortfolioController(private val service: PortfolioService) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody details: PortfolioDTO): PortfolioDTO {
        log.info("Running POST /portfolio method.")

        return service.create(details).also { log.info("Portfolio saved.") }
    }

    @GetMapping("/{id}")
    fun find(@PathVariable("id") id: UUID): PortfolioDTO {
        log.info("Running GET /portfolio/{id} method.")

        return service.find(id).also { log.info("Portfolio fetched.") }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: UUID, @RequestBody details: PortfolioDTO): PortfolioDTO {
        log.info("Running PUT /portfolio/{id} method.")

        return service.update(id, details).also { log.info("Portfolio updated.") }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: UUID) {
        log.info("Running DELETE /portfolio/{id} method.")

        service.delete(id).also { log.info("Portfolio deleted.") }
    }
}