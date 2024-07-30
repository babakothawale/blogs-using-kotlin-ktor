package com.bk.library.api.identity.model

import kotlinx.serialization.Serializable

data class User(val username: String, val email: String, val google: Boolean)


@Serializable
data class RegisterUserRequest(val username: String, val password: String, val email: String)

@Serializable
data class RegisterUserResponse(val username: String, val token: String, val error: String)


@Serializable
data class LoginRequest(val username: String, val password: String)

@Serializable
data class LoginResponse(val username: String, val token: String, val error: String)