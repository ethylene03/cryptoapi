package com.princess.cryptoapi.controller

import com.princess.cryptoapi.dto.HoldingDTO
import com.princess.cryptoapi.service.HoldingService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@Validated
@RequestMapping("/holdings")
class HoldingController(private val service: HoldingService) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun create(@RequestBody details: HoldingDTO): HoldingDTO {
        log.info("Running POST /holdings method.")

        return service.create(details, details.portfolioId)
            .also { log.info("Holding saved.") }
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: UUID, @RequestBody details: HoldingDTO): HoldingDTO {
        log.info("Running PUT /holdings/{id} method.")

        return service.update(details.copy(id = id))
            .also { log.info("Holding updated.") }
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: UUID) {
        log.info("Running DELETE /holdings/{id} method.")

        service.delete(id)
            .also { log.info("Holding deleted.") }
    }
}