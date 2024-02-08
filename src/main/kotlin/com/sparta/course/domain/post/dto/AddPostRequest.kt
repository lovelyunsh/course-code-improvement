package com.sparta.course.domain.post.dto


data class AddPostRequest(
    val userId: Long,
    var title: String,
    var content: String,
    var tag: String,
    var category: String,
    var status: String

)