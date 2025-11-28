package com.princess.cryptoapi.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "portfolio")
@EntityListeners(AuditingEntityListener::class)
class PortfolioEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var user: UserEntity? = null,

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