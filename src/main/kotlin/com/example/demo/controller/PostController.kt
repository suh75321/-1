package com.example.demo.controller

import com.example.demo.dto.PostCreateDto
import com.example.demo.dto.PostDto
import com.example.demo.dto.PostUpdateDto
import com.example.demo.model.Post
import com.example.demo.security.UserPrincipal
import com.example.demo.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/posts")
class PostController(private val postService: PostService) {

    @PostMapping
    fun createPost(@AuthenticationPrincipal user: UserPrincipal, @RequestBody createPostRequest: PostCreateDto
    ): ResponseEntity<Post> {
        val userId = user.id
        val postDto = postService.createPost(createPostRequest, userId.toString())
        return ResponseEntity.ok().body(postDto)
    }

    @GetMapping("/{postId}")
    fun getPost(@PathVariable postId: Long): ResponseEntity<PostDto> {
        val postDto = postService.getPost(postId)
        return ResponseEntity.ok().body(postDto)
    }

    @PutMapping("/{postId}")
    fun updatePost(@AuthenticationPrincipal user: UserPrincipal, @PathVariable postId: Long,
                   @RequestBody updatePostRequest: PostUpdateDto
    ): ResponseEntity<Post> {
        val userId = user.id
        val postDto = postService.updatePost(postId, updatePostRequest, userId.toString())
        return ResponseEntity.ok().body(postDto)
    }

    @GetMapping
    fun getAllPosts(): ResponseEntity<List<PostDto>> {
        val postDtos = postService.getAllPosts()
        return ResponseEntity.ok().body(postDtos)
    }

    @DeleteMapping("/{postId}")
    fun deletePost(@AuthenticationPrincipal user: UserPrincipal, @PathVariable postId: Long): ResponseEntity<Void> {
        val userId = user.id
        postService.deletePost(postId, userId.toString())
        return ResponseEntity.noContent().build()
    }
}