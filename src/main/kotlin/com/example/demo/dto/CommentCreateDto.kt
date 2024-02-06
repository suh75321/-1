package com.example.demo.dto

data class CommentCreateDto(
    val postId: Long,
    val nickname: String,
    val content: String
)
