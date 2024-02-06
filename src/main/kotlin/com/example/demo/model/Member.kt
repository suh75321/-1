package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "member")
class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "nick_name", nullable = false, unique = true)
    var nickName: String,

    @Column(name = "password", nullable = false)
    var password: String
)