package com.example.demo.dto

data class CommentCreateDto(
    val postId: Long,
    val nickname: String,
    val content: String,
    val isLike: Boolean   // 좋아요를 눌렀는지 여부를 표시하는 필드
)
