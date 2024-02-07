package com.example.demo.repository

import com.example.demo.model.Comment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByIdAndUserId(id: Long, userId: Long): Optional<Comment>
    fun findByPostId(postId: Long): List<Comment>// 이렇게 게시글에 댓글목록 가져옴

}
