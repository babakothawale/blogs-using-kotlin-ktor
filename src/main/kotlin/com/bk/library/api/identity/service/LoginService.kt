package com.bk.library.api.identity.service

import com.bk.library.api.identity.model.*

internal class LoginService(private val userRepository: UserRepository, private val session: SessionRepository) {
    internal suspend fun register(request: RegisterUserRequest): RegisterUserResponse {
        return try {
            val id = userRepository.register(User(request.username, request.email, false), request.password)
            if (id > 0) {
                val token = session.generateSession(username = request.username)
                RegisterUserResponse(username = request.username, token, "")
            } else {
                RegisterUserResponse(username = request.username, "", "Not able to register")
            }
        } catch (ex: Exception) {
            RegisterUserResponse(username = request.username, "", ex.message ?: "Error while registering")
        }
    }

    internal suspend fun login(request: LoginRequest): LoginResponse {
        return try {
            var token = session.getSession(username = request.username)
            if (token == null) {
                token = session.generateSession(username = request.username)
            }
            LoginResponse(username = request.username, token, "")
        } catch (ex: Exception) {
            LoginResponse(username = request.username, "", ex.message ?: "Error while login")
        }
    }
}