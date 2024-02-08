package com.example.demo.controller

import com.example.demo.dto.LoginRequest
import com.example.demo.dto.SignUpRequest
import com.example.demo.model.Member
import com.example.demo.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/members")
class MemberController(private val memberService: MemberService) {

    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<Member> {
        val member = memberService.signUp(signUpRequest)
        return ResponseEntity.ok(member)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): ResponseEntity<Any> {
        return try {
            val loginResponse = memberService.login(loginRequest)
            ResponseEntity.ok().body(loginResponse)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @GetMapping("/check-nickname")
    fun checkNickname(@RequestParam nickname: String): ResponseEntity<String> {
        return if (memberService.existsByNickName(nickname)) {
            ResponseEntity.badRequest().body("닉네임이 중복됩니다.")
        } else {
            ResponseEntity.ok().body("사용 가능한 닉네임입니다.")
        }
    }
}