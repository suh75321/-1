package com.example.demo.repository

import com.example.demo.model.Comment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByIdAndUserId(id: Long, memberId: Long): Optional<Comment>
    fun findAllByPostId(postId: Long): List<Comment>

}
