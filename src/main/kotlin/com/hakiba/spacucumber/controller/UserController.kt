package com.hakiba.spacucumber.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author hakiba
 */
@RestController
@RequestMapping("api/users")
class UserController {

    @CrossOrigin
    @PostMapping
    fun signUp(@RequestBody body: UserRegisterBody): ResponseEntity<Nothing> {
        println("$body.mailAddress $body.password")
        return ResponseEntity.ok().build()
    }

    @GetMapping("{id}")
    fun users(@PathVariable(value = "id") id: Long): ResponseEntity<UserDto> {
        return ResponseEntity.ok(UserDto(userId = id, mailAddress = "xxxxxx"))
    }
}

data class UserRegisterBody(
        val mailAddress: String,
        val password: String
)

data class UserDto(
        val userId: Long,
        val mailAddress: String
)