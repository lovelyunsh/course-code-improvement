package com.sparta.course.domain.courseApplication.dto

import com.sparta.course.domain.course.dto.CourseResponse
import com.sparta.course.domain.user.dto.UserResponse

data class CourseApplicationResponse(
    val id: Long,
    val course: CourseResponse,
    val user: UserResponse,
    val status: String,
)
