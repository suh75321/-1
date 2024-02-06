package com.example.demo.service

import com.example.demo.dto.CommentCreateDto
import com.example.demo.dto.CommentDto
import com.example.demo.dto.CommentUpdateDto
import com.example.demo.model.Comment
import com.example.demo.repository.CommentRepository
import com.example.demo.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val postRepository: PostRepository
) : CommentService {

    @Transactional
    override fun createComment(userId: Long, dto: CommentCreateDto): CommentDto {
        val post = postRepository.findById(dto.postId)
            .orElseThrow { IllegalArgumentException("Post not found") }

        val comment = Comment(
            post = post,
            userId = userId,
            nickname = dto.nickname,
            content = dto.content
        )

        commentRepository.save(comment)

        return CommentDto.from(comment)
    }

    @Transactional
    override fun updateComment(id: Long, userId: Long, dto: CommentUpdateDto): CommentDto {
        val comment = commentRepository.findByIdAndUserId(id, userId)
            .orElseThrow { IllegalArgumentException("Comment not found or not owned by user") }

        comment.content = dto.content

        return CommentDto.from(comment)
    }

    @Transactional(readOnly = true)
    override fun getComment(id: Long): CommentDto {
        val comment = commentRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Comment not found") }

        return CommentDto.from(comment)
    }

    @Transactional
    override fun deleteComment(id: Long, userId: Long) {
        val comment = commentRepository.findByIdAndUserId(id, userId)
            .orElseThrow { IllegalArgumentException("Comment not found or not owned by user") }

        commentRepository.deleteById(id)
    }
}