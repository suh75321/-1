package com.example.demo.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "post")
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "nickname", nullable = false)
    var nickname: String,

    @Column(name = "content", nullable = false)
    var content: String,

    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "member_id", nullable = false) // "user_id"를 "member_id"로 수정
    var memberId: Long // "userId"를 "memberId"로 수정
)