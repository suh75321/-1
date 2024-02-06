package com.example.demo.service

import com.example.demo.dto.PostCreateDto
import com.example.demo.dto.PostDto
import com.example.demo.dto.PostUpdateDto
import com.example.demo.model.Post
import com.example.demo.repository.MemberRepository
import com.example.demo.repository.PostRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(private val postRepository: PostRepository, private val memberRepository: MemberRepository) : PostService {

    @Transactional
    override fun createPost(createPostRequest: PostCreateDto, userId: Long): PostDto {
        // 사용자 조회
        val member = memberRepository.findById(userId)
            .orElseThrow { IllegalArgumentException("유효하지 않은 사용자입니다.") }

        // 게시글 생성
        val post = Post(
            title = createPostRequest.title,
            nickname = member.nickName,
            content = createPostRequest.content,
            userId = userId
        )

        // 게시글 저장
        val savedPost = postRepository.save(post)

        // 저장된 Post를 PostDto로 변환 후 반환
        return PostDto.from(savedPost)
    }

    override fun getPost(postId: Long): PostDto {
        val post = postRepository.findById(postId)
            .orElseThrow { IllegalArgumentException("해당 id의 게시글이 존재하지 않습니다.") }

        return PostDto.from(post)
    }

    override fun getAllPosts(): List<PostDto> {
        val posts = postRepository.findAllByOrderByCreatedAtDesc()
        return posts.map { PostDto.from(it) }
    }

    override fun updatePost(postId: Long, updatePostRequest: PostUpdateDto, userId: Long): PostDto {
        // 게시글 조회
        val post = postRepository.findById(postId)
            .orElseThrow { IllegalArgumentException("해당 id의 게시글이 존재하지 않습니다.") }

        // 사용자 검사
        if (post.userId != userId) {
            throw IllegalArgumentException("본인이 작성한 게시글만 수정할 수 있습니다.")
        }

        // 게시글 수정
        post.title = updatePostRequest.title
        post.content = updatePostRequest.content

        // 수정된 게시글 저장
        val updatedPost = postRepository.save(post)

        // 저장된 Post를 PostDto로 변환 후 반환
        return PostDto.from(updatedPost)
    }


    override fun deletePost(postId: Long, userId: Long) {
        // 게시글 조회
        val post = postRepository.findById(postId)
            .orElseThrow { IllegalArgumentException("해당 게시글이 존재하지 않습니다.") }

        // 사용자 검사
        if (post.userId != userId) {
            throw IllegalArgumentException("본인이 작성한 게시글만 삭제할 수 있습니다.")
        }

        // 게시글 삭제
        postRepository.deleteById(postId)
    }
}