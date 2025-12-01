package com.princess.cryptoapi.helpers

import com.princess.cryptoapi.dto.HoldingDTO
import com.princess.cryptoapi.dto.PortfolioDTO
import com.princess.cryptoapi.dto.UserDTO
import com.princess.cryptoapi.model.HoldingEntity
import com.princess.cryptoapi.model.PortfolioEntity
import com.princess.cryptoapi.model.UserEntity

fun UserEntity.toUserResponse(): UserDTO = UserDTO(
    id = this.id,
    name = this.name,
    username = this.username
)

fun UserDTO.createUserEntity(): UserEntity = UserEntity(
    name = this.name,
    username = this.username,
    password = this.password ?: throw IllegalArgumentException("Password is required.")
)

fun PortfolioEntity.toPortfolioResponse(): PortfolioDTO = PortfolioDTO(
    id = this.id,
    userId = this.user?.id ?: throw IllegalArgumentException("User is required."),
    name = this.name,
    holdings = this.holdings.map { it.toHoldingResponse() },
    totalAmount = this.holdings.sumOf { it.amount }
)

fun PortfolioDTO.createPortfolioEntity(user: UserEntity): PortfolioEntity = PortfolioEntity(
    user = user,
    name = this.name,
    holdings = mutableListOf()
)

fun HoldingEntity.toHoldingResponse(): HoldingDTO = HoldingDTO(
    id = this.id,
    assetId = this.assetId,
    amount = this.amount
)

fun HoldingDTO.createHoldingEntity(portfolio: PortfolioEntity): HoldingEntity = HoldingEntity(
    portfolio = portfolio,
    assetId = this.assetId,
    amount = this.amount
)