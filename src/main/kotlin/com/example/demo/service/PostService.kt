package com.example.demo.service

import com.example.demo.dto.PostCreateDto
import com.example.demo.dto.PostDto
import com.example.demo.dto.PostUpdateDto

interface PostService {
    fun createPost(createPostRequest: PostCreateDto, memberId: Long): PostDto
    fun getPost(postId: Long): PostDto
    fun updatePost(postId: Long, updatePostRequest: PostUpdateDto, memberId: Long): PostDto
    fun getAllPosts(): List<PostDto>
    fun deletePost(postId: Long, memberId: Long)
}