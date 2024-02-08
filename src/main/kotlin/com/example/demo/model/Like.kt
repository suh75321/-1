package com.example.demo.model

import jakarta.persistence.*

@Entity
@Table(name = "PostLike")
class Like(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    val member: Member,

    @ManyToOne
    val post: Post
)