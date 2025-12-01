package com.princess.cryptoapi.dto

import java.util.UUID

data class PortfolioDTO(
    val id: UUID? = null,
    val userId: UUID? = null,
    val name: String,
    val holdings: List<HoldingDTO>?,
    val totalAmount: Double
)