package com.princess.cryptoapi.service

import com.princess.cryptoapi.dto.ApiResponse
import com.princess.cryptoapi.webclient.AssetsApiClient
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class AssetsService(private val assetsApiClient: AssetsApiClient) {
    @Cacheable(cacheNames = ["assets/all"], key = "#params.toString()")
    fun findAll(params: Map<String, String>): ApiResponse =
        assetsApiClient.findAll(params).block()!!

    @Cacheable(cacheNames = ["assets/"], key = "#slug")
    fun find(slug: String): ApiResponse =
        assetsApiClient.find(slug).block()!!

    @Cacheable(cacheNames = ["assets/markets"], key = "#slug + '|' + #params.toString()")
    fun findMarkets(slug: String, params: Map<String, String>): ApiResponse =
        assetsApiClient.findMarkets(slug, params).block()!!

    @Cacheable(cacheNames = ["assets/history"], key = "#slug + '|' + #params.toString()")
    fun findHistory(slug: String, params: Map<String, String>): ApiResponse =
        assetsApiClient.findHistory(slug, params).block()!!
}