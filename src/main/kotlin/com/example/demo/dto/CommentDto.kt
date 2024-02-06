package com.example.demo.dto

import com.example.demo.model.Comment
import java.time.LocalDateTime

data class CommentDto(
    val id: Long,
    val postId: Long,
    val nickname: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(comment: Comment): CommentDto {
            return CommentDto(
                id = comment.id,
                postId = comment.post.id!!,
                nickname = comment.nickname,
                content = comment.content,
                createdAt = comment.createdAt,
                updatedAt = comment.updatedAt
            )
        }
    }
}
