package com.hakiba.spacucumber.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author hakiba
 */
@RestController
class HealthCheckController {
    @GetMapping("ok")
    fun ok(): ResponseEntity<Nothing> = ResponseEntity.ok().build()
}