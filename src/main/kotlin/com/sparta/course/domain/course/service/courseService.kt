package com.sparta.course.domain.course.service

import com.sparta.course.domain.course.dto.CourseResponse
import com.sparta.course.domain.course.dto.CreateCourseRequest
import com.sparta.course.domain.course.dto.UpdateCourseRequest
import com.sparta.course.domain.courseApplication.dto.ApplyCourseRequest
import com.sparta.course.domain.courseApplication.dto.CourseApplicationResponse
import com.sparta.course.domain.courseApplication.dto.UpdateApplicationStatusRequest
import com.sparta.course.domain.lecture.dto.AddLectureRequest
import com.sparta.course.domain.lecture.dto.LectureResponse
import com.sparta.course.domain.lecture.dto.UpdateLectureRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface CourseService {

    fun searchCourseList(title: String): List<CourseResponse>

    fun getPaginatedCourseList(pageable: Pageable, status: String?): Page<CourseResponse>

    fun getAllCourseList(): List<CourseResponse>

    fun getCourseById(courseId: Long): CourseResponse

    fun createCourse(request: CreateCourseRequest): CourseResponse

    fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse

    fun deleteCourse(courseId: Long)

    fun getLectureList(courseId: Long): List<LectureResponse>

    fun getLecture(courseId: Long, lectureId: Long): LectureResponse

    fun addLecture(courseId: Long, request: AddLectureRequest): LectureResponse

    fun updateLecture(
        courseId: Long,
        lectureId: Long,
        request: UpdateLectureRequest
    ): LectureResponse

    fun removeLecture(courseId: Long, lectureId: Long)

    fun applyCourse(courseId: Long, request: ApplyCourseRequest): CourseApplicationResponse

    fun getCourseApplication(
        courseId: Long,
        applicationId: Long
    ): CourseApplicationResponse

    fun getCourseApplicationList(courseId: Long): List<CourseApplicationResponse>

    fun updateCourseApplicationStatus(
        courseId: Long,
        applicationId: Long,
        request: UpdateApplicationStatusRequest
    ): CourseApplicationResponse

}
