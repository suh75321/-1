package com.example.demo.dto

import com.example.demo.model.Post
import java.time.LocalDateTime

data class PostDto(
    val id: Long,
    val title: String,
    val nickname: String,
    val content: String,
    val createdAt: LocalDateTime, // 추가된 필드
    val userId: Long  // 추가된 필드
) {
    companion object {
        fun from(post: Post): PostDto {
            return PostDto(
                id = post.id!!,
                title = post.title,
                nickname = post.nickname,
                content = post.content,
                createdAt = post.createdAt, // Post의 createdAt을 PostDto의 createdAt으로 설정
                userId = post.userId  // Post의 userId를 PostDto의 userId로 설정
            )
        }
    }
}