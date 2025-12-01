package com.princess.cryptoapi.service

import com.princess.cryptoapi.dto.HoldingDTO
import com.princess.cryptoapi.helpers.ResourceNotFoundException
import com.princess.cryptoapi.helpers.createHoldingEntity
import com.princess.cryptoapi.helpers.toHoldingResponse
import com.princess.cryptoapi.model.PortfolioEntity
import com.princess.cryptoapi.repository.HoldingRepository
import com.princess.cryptoapi.repository.PortfolioRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class HoldingService(
    private val holdingRepository: HoldingRepository, private val portfolioRepository: PortfolioRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun create(holding: HoldingDTO, portfolioId: UUID?): HoldingDTO {
        log.debug("Checking if portfolio exists..")
        val portfolio = portfolioId?.let {
            portfolioRepository.findById(it).orElseThrow {
                log.error("Portfolio does not exist.")
                ResourceNotFoundException("Portfolio does not exist.")
            }
        } ?: throw IllegalArgumentException("Portfolio ID is not provided.")

        log.debug("Saving holding..")
        return holding.createHoldingEntity(portfolio).let {
            holdingRepository.save(it)
        }.toHoldingResponse()
    }

    fun update(holding: HoldingDTO, portfolio: PortfolioEntity? = null): HoldingDTO {
        log.debug("Finding holding..")
        val recordedHolding = holding.id?.let {
            holdingRepository.findById(it).orElseThrow {
                log.error("Holding does not exist.")
                ResourceNotFoundException("Holding does not exist.")
            }
        } ?: run {
            log.debug("No holding id, saving as new one..")
            portfolio?.let { holding.createHoldingEntity(it) }
                ?: throw ResourceNotFoundException("No portfolio provided.")
        }

        return recordedHolding.apply {
            amount = holding.amount
        }.let {
            holdingRepository.save(it)
        }.toHoldingResponse()
    }

    fun delete(id: UUID) {
        log.debug("Finding holding..")
        holdingRepository.findById(id).orElseThrow {
            log.error("Holding does not exist.")
            ResourceNotFoundException("Holding does not exist.")
        }

        log.debug("Deleting holding..")
        holdingRepository.deleteById(id)
    }
}