package com.example.demo.dto

import com.example.demo.model.Post
import java.time.LocalDateTime

data class PostDto(
    val id: Long,
    val title: String,
    val nickname: String,
    val content: String,
    val createdAt: LocalDateTime
) {
    companion object {
        fun from(post: Post): PostDto {
            return PostDto(
                id = post.id!!,
                title = post.title,
                nickname = post.nickname,
                content = post.content,
                createdAt = post.createdAt
            )
        }
    }
}
