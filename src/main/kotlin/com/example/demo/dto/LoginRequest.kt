package com.example.demo.dto

import io.swagger.v3.oas.annotations.media.Schema

data class LoginRequest(
    @Schema(description = "nickName은 3자이상, 대소문자와 숫자로만 이루어져야 합니다", example = "Aa23")
    val nickName: String,
    @Schema(description = "비밀번호는 4자 이상, 닉네임과 같으면 회원가입 실패", example = "1234")
    val password: String,
    @Schema(description = "비밀번호 확인, 비밀번호와 일치해야 합니다", example = "1234")
    val passwordConfirm: String
)