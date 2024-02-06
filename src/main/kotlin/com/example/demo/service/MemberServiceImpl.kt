package com.example.demo.service

import com.example.demo.dto.LoginRequest
import com.example.demo.dto.SignUpRequest
import com.example.demo.jwt.JwtPlugin
import com.example.demo.model.Member
import com.example.demo.repository.MemberRepository
import org.springframework.stereotype.Service

@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val jwtPlugin: JwtPlugin
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

        val newMember = Member(nickName = signUpRequest.nickName, password = signUpRequest.password)
        return memberRepository.save(newMember)
    }

    override fun login(loginRequest: LoginRequest): String {
        val member = memberRepository.findByNickName(loginRequest.nickName)
            ?: throw IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요.")

        if (member.password != loginRequest.password) {
            throw IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요.")
        }

        return jwtPlugin.generateAccessToken(member.nickName)
    }
}