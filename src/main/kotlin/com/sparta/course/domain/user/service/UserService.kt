package com.sparta.course.domain.user.service

import com.sparta.course.domain.user.dto.*

interface UserService {

    // SignUpRequest 값을 이용해 회원가입
    fun signUp(signUpRequest: SignUpRequest): UserResponse

    // userId 값이 일치하는 User 데이터를 UpdateUserProfileRequest 값으로 수정
    fun updateUserProfile(userId: Long, updateUserProfileRequest: UpdateUserProfileRequest): UserResponse

    // LoginRequest 값을 비교하여 로그인 수행
    fun login(request: LoginRequest): LoginResponse
}