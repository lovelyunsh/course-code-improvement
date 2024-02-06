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

    // title을 contain하는 course 데이터 반환
    fun searchCourseList(title: String): List<CourseResponse>

    fun searchCourseList(userId: Long,status: String?): List<CourseResponse>

    // status 값이 일치하는 course 데이터에 paging 한 list를 반환
    fun getPaginatedCourseList(pageable: Pageable, status: String?): Page<CourseResponse>

    // 전체 course 데이터 list 반환
    fun getAllCourseList(): List<CourseResponse>

    // courseId 값이 일치하는 데이터 반환
    fun getCourseById(courseId: Long): CourseResponse

    // CreateCourseRequest 값을 insert
    fun createCourse(request: CreateCourseRequest): CourseResponse

    // courseId 값이 일치하는 데이터를 UpdateCourseRequest로 update
    fun updateCourse(courseId: Long, request: UpdateCourseRequest): CourseResponse

    // courseId 값이 일치하는 데이터를 삭제
    fun deleteCourse(courseId: Long)

    // courseId 값이 일치하는 데이터의 Lecture 데이터 반환
    fun getLectureList(courseId: Long): List<LectureResponse>

    // courseId 값이 일치하는 데이터의 Lecture 정보 중 lectureId 값이 일치하는 데이터 반환
    fun getLecture(courseId: Long, lectureId: Long): LectureResponse

    // courseId와 AddLectureRequest 값을 이용해 Lecture에 insert
    fun addLecture(courseId: Long, request: AddLectureRequest): LectureResponse

    // courseId와 lectureId가 일치하는 Lecture 데이터를 UpdateLectureRequest 데이터로 수정
    fun updateLecture(
        courseId: Long,
        lectureId: Long,
        request: UpdateLectureRequest
    ): LectureResponse

    // courseId와 lectureId가 일치하는 Lecture 데이터를 삭제
    fun removeLecture(courseId: Long, lectureId: Long)

    // courseId와 ApplyCourseRequest 값을 이용해 CourseApplication에 insert
    fun applyCourse(courseId: Long, request: ApplyCourseRequest): CourseApplicationResponse

    // courseId와 applicationId 일치하는 CourseApplication 데이터를 반환
    fun getCourseApplication(
        courseId: Long,
        applicationId: Long
    ): CourseApplicationResponse

    // courseId가 일치하는 CourseApplication 데이터 list 반환
    fun getCourseApplicationList(courseId: Long): List<CourseApplicationResponse>

    // courseId와 applicationId가 일치하는 CourseApplication 데이터의 상태를 UpdateApplicationStatusRequest 값으로 수정
    fun updateCourseApplicationStatus(
        courseId: Long,
        applicationId: Long,
        request: UpdateApplicationStatusRequest
    ): CourseApplicationResponse

}
