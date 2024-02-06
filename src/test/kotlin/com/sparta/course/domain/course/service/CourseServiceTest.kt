package com.sparta.course.domain.course.service
import com.sparta.course.domain.course.repository.CourseRepository
import com.sparta.course.domain.courseApplication.repository.CourseApplicationRepository
import com.sparta.course.domain.exception.ModelNotFoundException
import com.sparta.course.domain.lecture.repository.LectureRepository
import com.sparta.course.domain.user.repository.UserRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.extensions.spring.SpringExtension
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.repository.findByIdOrNull


@SpringBootTest
@ExtendWith(MockKExtension::class)
class CourseServiceTest  : BehaviorSpec({
    extension(SpringExtension)

		afterContainer {
        clearAllMocks()
    }

		// 종속 repository mocking
    val courseRepository = mockk<CourseRepository>()
    val lectureRepository = mockk<LectureRepository>()
    val applicationRepository = mockk<CourseApplicationRepository>()
    val userRepository = mockk<UserRepository>()

		// courseService 생성
    val courseService = CourseServiceImpl(courseRepository, lectureRepository, applicationRepository, userRepository)

    Given("Course 목록이 존재하지 않을때") {

        When("특정 Course를 요청하면") {

            Then("ModelNotFoundException이 발생해야 한다.") {
                val courseId = 1L
                every { courseRepository.findByIdOrNull(any()) } returns null

                shouldThrow<ModelNotFoundException> {
                    courseService.getCourseById(courseId)
                }
            }

        }
    }
})