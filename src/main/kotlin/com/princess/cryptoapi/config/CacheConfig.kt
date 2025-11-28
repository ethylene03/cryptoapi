package com.princess.cryptoapi.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class CacheConfig {

    @Bean
    fun caffeineCache(): Caffeine<Any, Any> =
        Caffeine.newBuilder()
            .expireAfterWrite(Duration.ofSeconds(30)) // Cache only 30 seconds
            .maximumSize(500)
}
