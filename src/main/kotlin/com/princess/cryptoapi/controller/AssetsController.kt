package com.princess.cryptoapi.controller

import com.princess.cryptoapi.service.AssetsService
import com.princess.cryptoapi.webclient.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/assets")
class AssetsController(private val assetsService: AssetsService) {
    private val log = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/{slug}/markets")
    fun getMarkets(@PathVariable slug: String, @RequestParam params: Map<String, String>): ApiResponse {
        log.info("Running GET /assets/{slug}/markets.")

        return assetsService.getMarkets(slug, params).also { log.info("Fetched.") }
    }

}