package com.hakiba.spacucumber.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author hakiba
 */
@CrossOrigin
@RestController
@RequestMapping("api/users")
class UserController {
    companion object {
        var userStore: UserDto? = null
    }

    @PostMapping
    fun signUp(@RequestBody body: UserRegisterBody): ResponseEntity<UserDto> {
        println("$body.mailAddress $body.password")
        val userDto = UserDto(id = "1", mailAddress = body.mailAddress)
        userStore = userDto
        return ResponseEntity.ok(userDto)
    }

    @GetMapping("{id}")
    fun users(@PathVariable(value = "id") id: String): ResponseEntity<UserDto> {
        return ResponseEntity.ok(UserDto(id = id, mailAddress = userStore?.mailAddress ?: ""))
    }
}

data class UserRegisterBody(
        val mailAddress: String,
        val password: String
)

data class UserDto(
        val id: String,
        val mailAddress: String
)
