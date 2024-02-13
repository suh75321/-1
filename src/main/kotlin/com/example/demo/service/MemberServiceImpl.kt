package com.example.demo.service

import com.example.demo.dto.LoginRequest
import com.example.demo.dto.LoginResponse
import com.example.demo.dto.SignUpRequest
import com.example.demo.jwt.JwtPlugin
import com.example.demo.model.Member
import com.example.demo.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val jwtPlugin: JwtPlugin,
    private val passwordEncoder: PasswordEncoder
): MemberService {
    override fun signUp(signUpRequest: SignUpRequest): Member {
        if (signUpRequest.nickName == signUpRequest.password) {
            throw IllegalArgumentException("비밀번호는 닉네임과 같을 수 없습니다.")
        }

        if (signUpRequest.password != signUpRequest.passwordConfirm) {
            throw IllegalArgumentException("비밀번호와 비밀번호 확인이 일치하지 않습니다.")
        }

        if (memberRepository.existsByNickName(signUpRequest.nickName)) {
            throw IllegalArgumentException("이미 존재하는 닉네임입니다.")
        }
        val encodedPassword = passwordEncoder.encode(signUpRequest.password)


        val newMember = Member(nickName = signUpRequest.nickName, password = encodedPassword) // 수정된 부분
        return memberRepository.save(newMember)
    }

    override fun login(loginRequest: LoginRequest): LoginResponse {
        val member = memberRepository.findByNickName(loginRequest.nickName)
            ?: throw IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요.")

        if (!passwordEncoder.matches(loginRequest.password, member.password)) {
            throw IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요.")
        }// 여기도 수정

        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = member.id.toString(),  // 사용자의 ID를 subject로 사용
                nickName = member.nickName  // 사용자의 닉네임을 nickName으로 사용
            )
        )
    }
    override fun existsByNickName(nickname: String): Boolean {
        return memberRepository.existsByNickName(nickname)
    }
}