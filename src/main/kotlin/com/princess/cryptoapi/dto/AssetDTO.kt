package com.princess.cryptoapi.dto

data class AssetParams(
    val search: String?,
    val ids: String?,
    val limit: Integer?,
    val offset: Integer?,
)

data class Asset(
    val id: String,
    val rank: String,
    val symbol: String,
    val name: String,
    val supply: String,
    val maxSupply: String,
    val marketCapUsd: String,
    val volumeUsd24Hr: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val vwap24Hr: String,
    val explorer: String,
    val tokens: Any,
)

data class AssetMarket(
    val exchangeId: String,
    val baseId: String,
    val quoteId: String,
    val baseSymbol: String,
    val quoteSymbol: String,
    val priceUsd: String,
    val volumeUsd24Hr: String,
    val volumePercent: String,
)

data class AssetHistory(
    val priceUsd: String,
    val time: Integer,
    val date: String,
)
