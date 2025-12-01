package com.princess.cryptoapi.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "holdings")
@EntityListeners(AuditingEntityListener::class)
class HoldingEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id")
    var portfolio: PortfolioEntity? = null,

    @Column(nullable = false)
    var assetId: String = "",

    @Column(nullable = false)
    var amount: Double = 0.0,

    @CreatedBy
    var createdBy: UUID? = null,

    @CreatedDate
    var createdAt: LocalDateTime? = null,

    @LastModifiedBy
    var modifiedBy: UUID? = null,

    @LastModifiedDate
    var modifiedAt: LocalDateTime? = null

)