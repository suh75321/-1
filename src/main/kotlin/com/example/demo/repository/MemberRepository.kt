package com.example.demo.repository

import com.example.demo.model.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByNickName(nickname: String): Member?
    fun existsByNickName(nickName: String): Boolean

}