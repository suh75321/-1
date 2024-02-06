package com.example.demo.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class SignUpRequest(
    @Schema(description = "memberName은 3자이상, 대소문자와 숫자로만 이루어져야 합니다", example = "Aa23")
    @field:Size(min = 3, message = "닉네임은 최소 3자 이상이어야 합니다.")
    @field:Pattern(regexp = "^[a-zA-Z0-9]*\$", message = "닉네임은 알파벳 대소문자와 숫자로만 이루어져야 합니다.")
    val nickName: String,

    @Schema(description = "비밀번호는 4자 이상, 닉네임과 같으면 회원가입 실패", example = "1234")
    @field:Size(min = 4, message = "비밀번호는 최소 4자 이상이어야 합니다.")
    val password: String,

    @Schema(description = "비밀번호 확인, 비밀번호와 일치해야 합니다", example = "1234")
    val passwordConfirm: String
)