package com.sparta.course.domain.lecture.repository

import com.sparta.course.domain.lecture.model.Lecture
import org.springframework.data.jpa.repository.JpaRepository

interface LectureRepository : JpaRepository<Lecture, Long> {

    fun findByCourseIdAndId(courseId: Long, lectureId: Long): Lecture?
}