package com.sparta.course.domain.course.repository

import com.sparta.course.domain.course.model.Course
import org.springframework.data.jpa.repository.JpaRepository


interface CourseRepository : JpaRepository<Course, Long>, CustomCourseRepository {}