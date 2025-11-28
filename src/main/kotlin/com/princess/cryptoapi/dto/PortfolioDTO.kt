package com.princess.cryptoapi.dto

import java.util.UUID

data class PortfolioDTO(
    val id: UUID? = null,
    val userId: UUID,
    val assetId: String,
    val amount: Double
)