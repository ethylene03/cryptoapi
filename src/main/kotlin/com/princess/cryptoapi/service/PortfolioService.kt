package com.princess.cryptoapi.service

import com.princess.cryptoapi.dto.PortfolioDTO
import com.princess.cryptoapi.helpers.ResourceNotFoundException
import com.princess.cryptoapi.helpers.createEntity
import com.princess.cryptoapi.helpers.toResponse
import com.princess.cryptoapi.repository.PortfolioRepository
import com.princess.cryptoapi.repository.UserRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class PortfolioService(
    private val portfolioRepository: PortfolioRepository, private val userRepository: UserRepository
) {
    private val log = LoggerFactory.getLogger(this::class.java)

    fun create(details: PortfolioDTO): PortfolioDTO {
        log.debug("Finding user..")
        val user = userRepository.findById(details.userId).orElseThrow {
            log.error("User does not exist.")
            ResourceNotFoundException("User does not exist.")
        }

        log.debug("Saving portfolio..")
        return details.createEntity(user).let { portfolioRepository.save(it) }.toResponse()
    }

    fun find(id: UUID): PortfolioDTO {
        log.debug("Finding portfolio..")
        return portfolioRepository.findById(id).orElseThrow {
            log.error("Portfolio does not exist.")
            ResourceNotFoundException("Portfolio does not exist.")
        }.toResponse()
    }

    fun findByUserId(userId: UUID): List<PortfolioDTO> {
        log.debug("Finding user..")
        userRepository.findById(userId).orElseThrow {
            log.error("User does not exist.")
            ResourceNotFoundException("User does not exist.")
        }

        log.debug("Fetching all portfolios of user..")
        return portfolioRepository.findAllByUserId(userId).map { it.toResponse() }
    }

    fun update(id: UUID, details: PortfolioDTO): PortfolioDTO {
        log.debug("Finding user..")
        val user = userRepository.findById(details.userId).orElseThrow {
            log.error("User does not exist.")
            ResourceNotFoundException("User does not exist.")
        }

        log.debug("Finding portfolio..")
        val portfolio = portfolioRepository.findById(id).orElseThrow {
            log.error("Portfolio does not exist.")
            ResourceNotFoundException("Portfolio does not exist.")
        }

        return portfolio.apply {
            amount = details.amount
        }.let { portfolioRepository.save(it) }.toResponse()
    }

    fun delete(id: UUID) {
        find(id)

        log.debug("Deleting portfolio..")
        portfolioRepository.deleteById(id)
    }
}