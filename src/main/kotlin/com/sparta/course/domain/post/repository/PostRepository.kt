package com.sparta.course.domain.post.repository

import com.sparta.course.domain.post.model.Post
import org.springframework.data.jpa.repository.JpaRepository


interface PostRepository : JpaRepository<Post, Long> {
}