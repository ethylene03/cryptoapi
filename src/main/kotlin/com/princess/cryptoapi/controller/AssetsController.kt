package com.princess.cryptoapi.controller

import com.princess.cryptoapi.dto.ApiResponse
import com.princess.cryptoapi.service.AssetsService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/assets")
class AssetsController(private val assetsService: AssetsService) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun findAll(@RequestParam params: Map<String, String>): ApiResponse {
        log.info("Running GET /assets method.")

        return assetsService.findAll(params).also { log.info("Fetched.") }
    }

    @GetMapping("/{slug}")
    fun find(@PathVariable slug: String): ApiResponse {
        log.info("Running GET /assets/{slug} method.")

        return assetsService.find(slug).also { log.info("Fetched.") }
    }

    @GetMapping("/{slug}/markets")
    fun getMarkets(@PathVariable slug: String, @RequestParam params: Map<String, String>): ApiResponse {
        log.info("Running GET /assets/{slug}/markets method.")

        return assetsService.findMarkets(slug, params).also { log.info("Fetched.") }
    }

    @GetMapping("/{slug}/history")
    fun findHistory(@PathVariable slug: String, @RequestParam params: Map<String, String>): ApiResponse {
        log.info("Running GET /assets/{slug}/history method.")

        return assetsService.findHistory(slug, params).also { log.info("Fetched.") }
    }

}