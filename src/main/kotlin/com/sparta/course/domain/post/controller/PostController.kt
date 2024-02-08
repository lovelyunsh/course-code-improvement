package com.sparta.course.domain.post.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/posts")
class PostController {

    @PostMapping
    fun createPost() {

    }

}