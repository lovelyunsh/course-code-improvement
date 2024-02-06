package com.sparta.course

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.EnableAspectJAutoProxy

@EnableAspectJAutoProxy
@SpringBootApplication
class CourseApplication

fun main(args: Array<String>) {
    runApplication<CourseApplication>(*args)
}