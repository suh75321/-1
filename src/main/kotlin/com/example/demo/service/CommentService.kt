package com.example.demo.service

import com.example.demo.dto.CommentCreateDto
import com.example.demo.dto.CommentDto
import com.example.demo.dto.CommentUpdateDto

interface CommentService {
    fun createComment(userId: Long, dto: CommentCreateDto): CommentDto
    fun updateComment(userId: Long, id: Long, dto: CommentUpdateDto): CommentDto
    fun getComment(id: Long): CommentDto
    fun deleteComment(userId: Long, id: Long)
}
