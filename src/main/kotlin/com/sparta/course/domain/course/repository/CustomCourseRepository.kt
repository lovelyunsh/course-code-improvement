package com.sparta.course.domain.course.repository

import com.sparta.course.domain.course.model.Course
import com.sparta.course.domain.course.model.CourseStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CustomCourseRepository {

    fun searchCourseListByTitle(title: String): List<Course>

    fun findByPageableAndStatus(pageable: Pageable, status: CourseStatus?): Page<Course>


}