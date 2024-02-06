package com.sparta.course.domain.courseApplication.repository

import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.BooleanExpression
import com.sparta.course.domain.course.model.Course
import com.sparta.course.domain.course.model.QCourse
import com.sparta.course.domain.courseApplication.model.CourseApplicationStatus
import com.sparta.course.domain.courseApplication.model.QCourseApplication
import com.sparta.course.domain.lecture.model.QLecture
import com.sparta.course.infra.querydsl.QueryDslSupport

class CustomCourseApplicationRepositoryImpl : CustomCourseApplicationRepository, QueryDslSupport() {

    private val courseApplication = QCourseApplication.courseApplication
    override fun searchCourseApplicationByUserIdAndStatus(
        userId: Long,
        status: CourseApplicationStatus?
    ): List<Course> {
        val course = QCourse.course
        val lecture = QLecture.lecture

        return queryFactory.select(course)
            .from(courseApplication)
            .leftJoin(courseApplication.course, course)
            .leftJoin(courseApplication.course.lectures, lecture)
            .fetchJoin()
            .where(statusEqAndUserIdEq(status, userId))
            .fetch()
    }

    private fun statusEqAndUserIdEq(status: CourseApplicationStatus?, userId: Long): BooleanBuilder {
        return BooleanBuilder()
            .and(statusEq(status))
            .and(userIdEq(userId))
    }

    private fun statusEq(status: CourseApplicationStatus?): BooleanExpression? {
        return status?.let { courseApplication.status.eq(status) }
    }

    private fun userIdEq(userId: Long): BooleanExpression {
        return courseApplication.user.id.eq(userId)
    }
}