package com.princess.cryptoapi.dto

import java.util.UUID

data class HoldingDTO(
    val id: UUID? = null,
    val portfolioId: UUID? = null,
    val assetId: String,
    val amount: Double,
)