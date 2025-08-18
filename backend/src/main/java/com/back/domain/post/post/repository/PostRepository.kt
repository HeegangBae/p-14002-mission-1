package com.back.domain.post.post.repository

import com.back.domain.post.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Int> {
    fun findNullableById(id: Int): Post = findById(id).orElse(null)
    fun findFirstByOrderByIdDesc(): Post?
}