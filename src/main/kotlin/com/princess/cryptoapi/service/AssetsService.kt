package com.princess.cryptoapi.service

import com.princess.cryptoapi.webclient.ApiResponse
import com.princess.cryptoapi.webclient.AssetsApiClient
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class AssetsService(private val assetsApiClient: AssetsApiClient) {
    @Cacheable(cacheNames = ["markets"], key = "#slug + '|' + #params.toString()")
    fun getMarkets(slug: String, params: Map<String, String>): ApiResponse =
        assetsApiClient.getMarkets(slug, params).block()!!
}