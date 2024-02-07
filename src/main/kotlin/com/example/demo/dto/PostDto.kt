package com.example.demo.dto

import com.example.demo.model.Post
import java.time.LocalDateTime

data class PostDto(
    val id: Long,
    val title: String,
    val nickname: String,
    val content: String,
    val createdAt: LocalDateTime,
    val userId: Long,
    val comments: List<CommentDto>  // 이 부분을 추가
) {
    companion object {
        fun from(post: Post, comments: List<CommentDto> = listOf()): PostDto {
            return PostDto(
                id = post.id!!,
                title = post.title,
                nickname = post.nickname,
                content = post.content,
                createdAt = post.createdAt,
                userId = post.userId,
                comments = comments //여기도 추가
            )
        }
    }
}