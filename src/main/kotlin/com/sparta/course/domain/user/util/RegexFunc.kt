package com.sparta.course.domain.user.util

import org.springframework.stereotype.Component
import java.util.regex.Pattern

@Component
class RegexFunc {
    fun regexEmail(email: String): Boolean {
        val regexEmail = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$"
        return Pattern.matches(regexEmail, email)
    }

    fun regexPassword(password: String): Boolean {
        val regexPassword = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#%^&*])[a-zA-Z0-9!@#%^&*]{8,15}\$"
        return Pattern.matches(regexPassword, password)
    }

}