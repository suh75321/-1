package com.example.demo.service

import com.example.demo.dto.PostCreateDto
import com.example.demo.dto.PostDto
import com.example.demo.dto.PostUpdateDto
import com.example.demo.model.Post

interface PostService {
    fun createPost(createPostRequest: PostCreateDto, token: String): Post
    fun getPost(id: Long): PostDto
    fun getAllPosts(): List<PostDto>
    fun updatePost(id: Long, updatePostRequest: PostUpdateDto, token: String): Post
    fun deletePost(postId: Long, token: String)
}