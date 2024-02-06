package com.sparta.course.domain.courseApplication.repository

import com.sparta.course.domain.course.model.Course
import com.sparta.course.domain.courseApplication.model.CourseApplicationStatus

interface CustomCourseApplicationRepository {
    fun searchCourseApplicationByUserIdAndStatus(userId:Long,status:CourseApplicationStatus?):List<Course>

}