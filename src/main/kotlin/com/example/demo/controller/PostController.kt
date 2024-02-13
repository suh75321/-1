package com.example.demo.controller

import com.example.demo.dto.CommentDto
import com.example.demo.dto.PostCreateDto
import com.example.demo.dto.PostDto
import com.example.demo.dto.PostUpdateDto
import com.example.demo.security.MemberPrincipal
import com.example.demo.service.CommentServiceImpl
import com.example.demo.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(
    private val postService: PostService,
    private val commentService: CommentServiceImpl
) {

    @PostMapping
    fun createPost(@AuthenticationPrincipal member: MemberPrincipal,
                   @RequestBody createPostRequest: PostCreateDto): ResponseEntity<PostDto> {
        val memberId = member.id
        val postDto = postService.createPost(createPostRequest, memberId)
        return ResponseEntity.ok().body(postDto)
    }

    @GetMapping("/{postId}")
    fun getPost(@PathVariable postId: Long): ResponseEntity<PostDto> {
        val postDto = postService.getPost(postId)
        return ResponseEntity.ok().body(postDto)
    }

    @PutMapping("/{postId}")
    fun updatePost(@AuthenticationPrincipal member: MemberPrincipal, @PathVariable postId: Long,
                   @RequestBody updatePostRequest: PostUpdateDto
    ): ResponseEntity<PostDto> {
        val memberId = member.id
        val postDto = postService.updatePost(postId, updatePostRequest, memberId)
        return ResponseEntity.ok().body(postDto)
    }

    @GetMapping
    fun getAllPosts(): ResponseEntity<List<PostDto>> {
        val postDtos = postService.getAllPosts()
        return ResponseEntity.ok().body(postDtos)
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@AuthenticationPrincipal member: MemberPrincipal, @PathVariable postId: Long): ResponseEntity<Void> {
        val memberId = member.id
        postService.deletePost(postId, memberId)
        return ResponseEntity.noContent().build()
    }
    @GetMapping("/{postId}/comments")
    fun getCommentsByPost(@PathVariable postId: Long): ResponseEntity<List<CommentDto>> {
        val commentDtos = commentService.getCommentsByPostId(postId)
        return ResponseEntity.ok().body(commentDtos)
    }
}