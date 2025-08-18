package com.back.domain.member.member.repository

import com.back.domain.member.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface MemberRepository : JpaRepository<Member, Int> {
    fun findNullableById(id: Int): Member? = findById(id).orElse(null)
    fun findByUsername(username: String): Member?
    fun findByApiKey(apiKey: String): Member?
}