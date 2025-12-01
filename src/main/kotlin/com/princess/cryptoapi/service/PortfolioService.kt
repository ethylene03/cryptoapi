package com.princess.cryptoapi.service

import com.princess.cryptoapi.dto.PortfolioDTO
import com.princess.cryptoapi.helpers.ResourceNotFoundException
import com.princess.cryptoapi.helpers.createPortfolioEntity
import com.princess.cryptoapi.helpers.toPortfolioResponse
import com.princess.cryptoapi.repository.PortfolioRepository
import com.princess.cryptoapi.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class PortfolioService(
    private val portfolioRepository: PortfolioRepository,
    private val userRepository: UserRepository,
    private val holdingService: HoldingService
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun create(details: PortfolioDTO, userId: UUID): PortfolioDTO {
        log.debug("Finding user..")
        val user = userRepository.findById(userId).orElseThrow {
            log.error("User does not exist.")
            ResourceNotFoundException("User does not exist.")
        }

        log.debug("Saving portfolio..")
        val portfolio = details.createPortfolioEntity(user).let { portfolioRepository.save(it) }

        log.debug("Saving holdings..")
        details.holdings?.forEach {
            holdingService.create(it, portfolio.id)
        }

        return portfolioRepository.findById(portfolio.id!!).orElseThrow {
            log.error("Portfolio was not created.")
            ResourceNotFoundException("Portfolio was not created.")
        }.toPortfolioResponse()
    }

    fun find(id: UUID): PortfolioDTO {
        log.debug("Finding portfolio..")
        return portfolioRepository.findById(id).orElseThrow {
            log.error("Portfolio does not exist.")
            ResourceNotFoundException("Portfolio does not exist.")
        }.toPortfolioResponse()
    }

    fun findByUserId(userId: UUID): List<PortfolioDTO> {
        log.debug("Finding user..")
        userRepository.findById(userId).orElseThrow {
            log.error("User does not exist.")
            ResourceNotFoundException("User does not exist.")
        }

        log.debug("Fetching all portfolios of user..")
        return portfolioRepository.findAllByUserId(userId).map { it.toPortfolioResponse() }
    }

    fun update(id: UUID, details: PortfolioDTO, userId: UUID): PortfolioDTO {
        log.debug("Finding user..")
        val user = userRepository.findById(userId).orElseThrow {
            log.error("User does not exist.")
            ResourceNotFoundException("User does not exist.")
        }

        log.debug("Finding portfolio..")
        val portfolio = portfolioRepository.findById(id).orElseThrow {
            log.error("Portfolio does not exist.")
            ResourceNotFoundException("Portfolio does not exist.")
        }

        log.debug("Updating holdings..")
        details.holdings?.forEach { holdingService.update(it, portfolio) }

        log.debug("Saving changes..")
        return portfolio.apply {
            name = details.name
        }.let { portfolioRepository.save(it) }.toPortfolioResponse()
    }

    fun delete(id: UUID) {
        find(id)

        log.debug("Deleting portfolio..")
        portfolioRepository.deleteById(id)
    }
}