package com.sparta.course.domain.courseApplication.repository

import com.sparta.course.domain.courseApplication.model.CourseApplication
import org.springframework.data.jpa.repository.JpaRepository

interface CourseApplicationRepository : JpaRepository<CourseApplication, Long> {

    fun existsByCourseIdAndUserId(courseId: Long, userId: Long): Boolean

    fun findByCourseIdAndId(courseId: Long, id: Long): CourseApplication?
}