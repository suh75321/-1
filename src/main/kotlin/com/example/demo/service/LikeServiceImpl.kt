package com.example.demo.service
import com.example.demo.model.Like
import com.example.demo.repository.LikeRepository
import com.example.demo.repository.MemberRepository
import com.example.demo.repository.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LikeServiceImpl(
    private val postRepository: PostRepository,
    private val memberRepository: MemberRepository,
    private val likeRepository: LikeRepository
) : LikeService {

    @Transactional
    override fun addLike(memberId: Long, postId: Long) {
        val member = memberRepository.findById(memberId).orElseThrow {
            IllegalArgumentException("해당하는 회원이 없습니다.")
        }
        val post = postRepository.findById(postId).orElseThrow {
            IllegalArgumentException("해당하는 게시글이 없습니다.")
        }

        val like = Like(member = member, post = post)
        likeRepository.save(like)
    }

}