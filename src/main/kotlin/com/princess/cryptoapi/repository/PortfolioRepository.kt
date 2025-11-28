package com.princess.cryptoapi.repository

import com.princess.cryptoapi.model.PortfolioEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface PortfolioRepository : JpaRepository<PortfolioEntity, UUID> {
    fun findAllByUserId(userId: UUID): List<PortfolioEntity>
}