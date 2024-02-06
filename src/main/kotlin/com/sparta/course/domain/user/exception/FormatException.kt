package com.sparta.course.domain.user.exception

data class FormatException(
    override val message: String? = "Please enter it in the correct format."
): RuntimeException()