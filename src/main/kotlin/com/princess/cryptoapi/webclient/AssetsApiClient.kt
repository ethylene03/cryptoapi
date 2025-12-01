package com.princess.cryptoapi.webclient

import com.princess.cryptoapi.dto.ApiResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import kotlin.collections.component1
import kotlin.collections.component2

@Component
class AssetsApiClient(builder: WebClient.Builder, @Value("\${coincap.key}") private val CoinCapKey: String) {
    private val log = LoggerFactory.getLogger(this::class.java)

    private val client = builder.baseUrl("https://rest.coincap.io/v3")
        .defaultHeader("Authorization", "Bearer $CoinCapKey")
        .build()

    fun findAll(params: Map<String, String>): Mono<ApiResponse> =
        client.get().uri { builder ->
            builder.path("/assets")
                .apply {
                    params.forEach { (key, value) -> queryParam(key, value) }
                }.build()
        }.retrieve()
            .bodyToMono(ApiResponse::class.java)

    fun find(slug: String): Mono<ApiResponse> =
        client.get().uri { builder ->
            builder.path("/assets/$slug").build()
        }.retrieve()
            .bodyToMono(ApiResponse::class.java)


    fun findMarkets(slug: String, params: Map<String, String>): Mono<ApiResponse> =
        client.get().uri { builder ->

            builder.path("/assets/$slug/markets")
                .apply {
                    params.forEach { (key, value) -> queryParam(key, value) }
                }.build()

        }.retrieve()
            .bodyToMono(ApiResponse::class.java)


    fun findHistory(slug: String, params: Map<String, String>): Mono<ApiResponse> =
        client.get().uri { builder ->

            builder.path("/assets/$slug/history")
                .apply {
                    params.forEach { (key, value) -> queryParam(key, value) }
                }.build()

        }.retrieve()
            .bodyToMono(ApiResponse::class.java)
}