package com.princess.cryptoapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class CryptoapiApplication

fun main(args: Array<String>) {
	runApplication<CryptoapiApplication>(*args)
}
