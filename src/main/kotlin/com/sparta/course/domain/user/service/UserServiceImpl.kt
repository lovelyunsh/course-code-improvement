package com.sparta.course.domain.user.service

import com.sparta.course.domain.exception.ModelNotFoundException
import com.sparta.course.domain.user.dto.*
import com.sparta.course.domain.user.exception.FormatException
import com.sparta.course.domain.user.exception.InvalidCredentialException
import com.sparta.course.domain.user.model.Profile
import com.sparta.course.domain.user.model.User
import com.sparta.course.domain.user.model.UserRole
import com.sparta.course.domain.user.model.toResponse
import com.sparta.course.domain.user.repository.UserRepository
import com.sparta.course.domain.user.util.RegexFunc
import com.sparta.course.infra.security.jwt.JwtPlugin
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin,
    private val regexFunc: RegexFunc
) : UserService {

    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email) ?: throw ModelNotFoundException("User", null)

        if (user.role.name != request.role || !passwordEncoder.matches(request.password, user.password)) {
            throw InvalidCredentialException()
        }

        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role.name
            )
        )
    }

    override fun signUp(request: SignUpRequest): UserResponse {
        request.checkEmailFormat()
            .checkPasswordFormat()
            .checkEmailDuplication()

        return userRepository.save(
            User(
                email = request.email,
                password = passwordEncoder.encode(request.password),
                profile = Profile(
                    nickname = request.nickname
                ),
                role = when (request.role) {
                    "STUDENT" -> UserRole.STUDENT
                    "TUTOR" -> UserRole.TUTOR
                    else -> throw IllegalArgumentException("Invalid role")
                }
            )
        ).toResponse()
    }

    override fun updateUserProfile(userId: Long, updateUserProfileRequest: UpdateUserProfileRequest): UserResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)
        user.profile = Profile(
            nickname = updateUserProfileRequest.nickname
        )

        return userRepository.save(user).toResponse()
    }

    private fun SignUpRequest.checkEmailFormat(): SignUpRequest {
        if (!regexFunc.regexEmail(this.email)) {
            throw FormatException("Please enter your email using the correct email format.")
        }
        return this
    }

    private fun SignUpRequest.checkPasswordFormat(): SignUpRequest {
        if (!regexFunc.regexPassword(this.password)) {
            throw FormatException(
                "Please enter 8 to 15 characters including letters, numbers, and special characters."
            )
        }
        return this
    }

    private fun SignUpRequest.checkEmailDuplication(): SignUpRequest {
        if (userRepository.existsByEmail(this.email)) {
            throw IllegalStateException("Email is already in use")
        }
        return this
    }

}