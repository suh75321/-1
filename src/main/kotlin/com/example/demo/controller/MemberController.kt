package com.example.demo.controller

import com.example.demo.dto.LoginRequest
import com.example.demo.dto.SignUpRequest
import com.example.demo.model.Member
import com.example.demo.service.MemberService
import jakarta.servlet.http.Cookie
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService
) {
    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<Member> {
        val member = memberService.signUp(signUpRequest)
        return ResponseEntity.ok(member)
    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest, response: HttpServletResponse): ResponseEntity<Any> {
        return try {
            val jwt = memberService.login(loginRequest)

            val cookie = Cookie("jwt", jwt.toString())
            cookie.isHttpOnly = true
            response.addCookie(cookie)

            ResponseEntity.ok().build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }
}