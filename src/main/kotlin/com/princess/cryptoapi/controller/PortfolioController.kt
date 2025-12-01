package com.princess.cryptoapi.controller

import com.princess.cryptoapi.dto.PortfolioDTO
import com.princess.cryptoapi.service.PortfolioService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@Validated
@RequestMapping("/portfolios")
class PortfolioController(private val service: PortfolioService) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody details: PortfolioDTO, @AuthenticationPrincipal userId: UUID): PortfolioDTO {
        log.info("Running POST /portfolio method.")

        return service.create(details, userId).also { log.info("Portfolio saved.") }
    }

    @GetMapping
    fun findAll(@AuthenticationPrincipal userId: UUID): List<PortfolioDTO> {
        log.info("Running GET /portfolio/{id} method.")

        return service.findByUserId(userId).also { log.info("Portfolios fetched.") }
    }

    @GetMapping("/{id}")
    fun find(@PathVariable("id") id: UUID): PortfolioDTO {
        log.info("Running GET /portfolio/{id} method.")

        return service.find(id).also { log.info("Portfolio fetched.") }
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable("id") id: UUID,
        @RequestBody details: PortfolioDTO,
        @AuthenticationPrincipal userId: UUID
    ): PortfolioDTO {
        log.info("Running PUT /portfolio/{id} method.")

        return service.update(id, details, userId).also { log.info("Portfolio updated.") }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: UUID) {
        log.info("Running DELETE /portfolio/{id} method.")

        service.delete(id).also { log.info("Portfolio deleted.") }
    }
}