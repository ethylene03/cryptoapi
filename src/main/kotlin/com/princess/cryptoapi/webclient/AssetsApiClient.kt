package com.princess.cryptoapi.webclient

import com.princess.cryptoapi.dto.AssetMarket
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

data class ApiResponse(
    val data: List<AssetMarket>, val timestamp: Long
)

@Component
class AssetsApiClient(builder: WebClient.Builder, @Value("\${coincap.key}") private val CoinCapKey: String) {

    private val client = builder.baseUrl("https://rest.coincap.io/v3")
            .defaultHeader("Authorization", "Bearer $CoinCapKey")
            .build()

    fun findAll(params: Map<String, String>)

    fun findMarkets(slug: String, params: Map<String, String>): Mono<ApiResponse> =
        client.get().uri { builder ->

            builder.path("/assets/$slug/markets")
                .apply {
                    params.forEach { (key, value) -> queryParam(key, value) }
                }.build()

        }.retrieve()
            .bodyToMono(ApiResponse::class.java)
}