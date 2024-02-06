package com.sparta.course.domain.course.controller

import com.sparta.course.domain.course.dto.CourseResponse
import com.sparta.course.domain.course.dto.CreateCourseRequest
import com.sparta.course.domain.course.dto.UpdateCourseRequest
import com.sparta.course.domain.course.service.CourseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RequestMapping("/courses")
@RestController
class CourseController(
    private val courseService: CourseService
) {

    @GetMapping()
    @PreAuthorize("hasRole('TUTOR') or hasRole('STUDENT')")
    fun getPaginatedCourseList(
        @PageableDefault(size = 15, sort = ["id"]) pageable: Pageable,
        @RequestParam(value = "status", required = false) status: String?
    ): ResponseEntity<Page<CourseResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getPaginatedCourseList(pageable, status))
    }


    @GetMapping("/search")
    @PreAuthorize("hasRole('TUTOR') or hasRole('STUDENT')")
    fun searchCourseList(@RequestParam(name = "title") title: String): ResponseEntity<List<CourseResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.searchCourseList(title))
    }

    @GetMapping("/{courseId}")
    @PreAuthorize("hasRole('TUTOR') or hasRole('STUDENT')")
    fun getCourse(@PathVariable courseId: Long): ResponseEntity<CourseResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.getCourseById(courseId))
    }

    @PostMapping
    @PreAuthorize("hasRole('TUTOR')")
    fun createCourse(@RequestBody createCourseRequest: CreateCourseRequest): ResponseEntity<CourseResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(courseService.createCourse(createCourseRequest))
    }

    @PutMapping("/{courseId}")
    @PreAuthorize("hasRole('TUTOR')")
    fun updateCourse(
        @PathVariable courseId: Long,
        @RequestBody updateCourseRequest: UpdateCourseRequest
    ): ResponseEntity<CourseResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(courseService.updateCourse(courseId, updateCourseRequest))
    }

    @DeleteMapping("/{courseId}")
    @PreAuthorize("hasRole('TUTOR')")
    fun deleteCourse(@PathVariable courseId: Long): ResponseEntity<Unit> {
        courseService.deleteCourse(courseId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}
