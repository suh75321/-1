package com.example.demo.service

import com.example.demo.dto.PostCreateDto
import com.example.demo.dto.PostDto
import com.example.demo.dto.PostUpdateDto
import com.example.demo.jwt.JwtPlugin
import com.example.demo.model.Post
import com.example.demo.repository.MemberRepository
import com.example.demo.repository.PostRepository
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val jwtPlugin: JwtPlugin,
    private val memberRepository: MemberRepository
): PostService {
    override fun createPost(createPostRequest: PostCreateDto, token: String): Post {
        // 토큰 검사
        val nickName = jwtPlugin.validateToken(token)

        // 토큰에 담긴 닉네임으로 사용자 조회
        val member = memberRepository.findByNickName(nickName.toString())
            ?: throw IllegalArgumentException("유효하지 않은 사용자입니다.")

        // 제목과 내용의 길이 검사
        if (createPostRequest.title.length > 500) {
            throw IllegalArgumentException("제목은 500자를 초과할 수 없습니다.")
        }

        if (createPostRequest.content.length > 5000) {
            throw IllegalArgumentException("내용은 5000자를 초과할 수 없습니다.")
        }

        // 게시글 작성
        val post = Post(
            title = createPostRequest.title,
            nickname = member.nickName,
            content = createPostRequest.content,
        )

        return postRepository.save(post)
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

    override fun updatePost(postId: Long, updatePostRequest: PostUpdateDto, token: String): Post {
        // 토큰 검사
        val nickName = jwtPlugin.validateToken(token)

        // 토큰에 담긴 닉네임으로 사용자 조회
        val member = memberRepository.findByNickName(nickName.toString())
            ?: throw IllegalArgumentException("유효하지 않은 사용자입니다.")

        // 게시글 조회
        val post = postRepository.findById(postId)
            .orElseThrow { IllegalArgumentException("해당 게시글이 존재하지 않습니다.") }

        // 사용자 검사
        if (post.nickname != member.nickName) {
            throw IllegalArgumentException("본인이 작성한 게시글만 수정할 수 있습니다.")
        }

        // 게시글 수정
        post.title = updatePostRequest.title
        post.content = updatePostRequest.content

        return postRepository.save(post)
    }

    override fun deletePost(postId: Long, userId: Long) {
        // 사용자 조회
        val member = memberRepository.findById(userId)
            ?: throw IllegalArgumentException("유효하지 않은 사용자입니다.")

        // 게시글 조회
        val post = postRepository.findById(postId)
            .orElseThrow { IllegalArgumentException("해당 게시글이 존재하지 않습니다.") }

        // 사용자 검사
        if (post.nickname != member.nickName) {
            throw IllegalArgumentException("본인이 작성한 게시글만 삭제할 수 있습니다.")
        }

        // 게시글 삭제
        postRepository.deleteById(postId)
    }
}