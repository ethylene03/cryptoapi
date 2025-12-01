package com.princess.cryptoapi.repository

import com.princess.cryptoapi.model.HoldingEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface HoldingRepository : JpaRepository<HoldingEntity, UUID> {
}