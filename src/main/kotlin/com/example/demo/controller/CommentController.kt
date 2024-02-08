package com.example.demo.controller

import com.example.demo.dto.CommentCreateDto
import com.example.demo.dto.CommentDto
import com.example.demo.dto.CommentUpdateDto
import com.example.demo.security.UserPrincipal
import com.example.demo.service.CommentService
import com.example.demo.service.LikeServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/comments")
class CommentController(
    private val commentService: CommentService,
    private val likeService: LikeServiceImpl
) {

    @PostMapping
    fun createCommentAndLikePost(@AuthenticationPrincipal user: UserPrincipal, @RequestBody dto: CommentCreateDto, @RequestParam postId: Long): ResponseEntity<CommentDto> {
        val userId = user.id
        val commentDto = commentService.createComment(userId, dto)
        likeService.addLike(userId, postId)
        return ResponseEntity.ok(commentDto)
    }

    @PutMapping("/{id}")
    fun updateComment(@AuthenticationPrincipal user: UserPrincipal,@PathVariable id: Long, @RequestBody dto: CommentUpdateDto): ResponseEntity<CommentDto> {
        val userId = user.id
        val commentDto = commentService.updateComment(userId, id, dto)
        return ResponseEntity.ok(commentDto)
    }

    @GetMapping("/{id}")
    fun getComment(@PathVariable id: Long): ResponseEntity<CommentDto> {
        val commentDto = commentService.getComment(id)
        return ResponseEntity.ok(commentDto)
    }

    @DeleteMapping("/{id}")
    fun deleteComment(@AuthenticationPrincipal user: UserPrincipal,@PathVariable id: Long): ResponseEntity<Unit> {
        val userId = user.id
        commentService.deleteComment(id, userId)
        return ResponseEntity.noContent().build()
    }
}