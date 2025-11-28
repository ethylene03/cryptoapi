package com.princess.cryptoapi.helpers

import com.princess.cryptoapi.dto.PortfolioDTO
import com.princess.cryptoapi.dto.UserDTO
import com.princess.cryptoapi.model.PortfolioEntity
import com.princess.cryptoapi.model.UserEntity

fun UserEntity.toResponse(): UserDTO = UserDTO(
    id = this.id,
    name = this.name,
    username = this.username
)

fun UserDTO.createEntity(): UserEntity = UserEntity(
    name = this.name,
    username = this.username,
    password = this.password ?: throw IllegalArgumentException("Password is required.")
)

fun PortfolioEntity.toResponse(): PortfolioDTO = PortfolioDTO(
    id = this.id,
    userId = this.user?.id ?: throw IllegalArgumentException("User is required."),
    assetId = this.assetId,
    amount = this.amount
)

fun PortfolioDTO.createEntity(user: UserEntity): PortfolioEntity = PortfolioEntity(
    user = user,
    assetId = this.assetId,
    amount = this.amount
)