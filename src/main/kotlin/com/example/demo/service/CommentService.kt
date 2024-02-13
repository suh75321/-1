package com.example.demo.service

import com.example.demo.dto.CommentCreateDto
import com.example.demo.dto.CommentDto
import com.example.demo.dto.CommentUpdateDto

interface CommentService {
    fun createComment(memberId: Long, dto: CommentCreateDto): CommentDto
    fun updateComment(memberId: Long, id: Long, dto: CommentUpdateDto): CommentDto
    fun getComment(id: Long): CommentDto
    fun deleteComment(memberId: Long, id: Long)
    fun getCommentsByPostId(postId: Long): List<CommentDto>// 추가함

}
