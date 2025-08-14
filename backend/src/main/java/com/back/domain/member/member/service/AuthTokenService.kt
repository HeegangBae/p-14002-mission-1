package com.back.domain.member.member.service

import com.back.domain.member.member.entity.Member
import com.back.standard.util.Ut
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class AuthTokenService {
    @Value("\${custom.jwt.secretKey}")
    private val jwtSecretKey: String? = null

    @Value("\${custom.accessToken.expirationSeconds}")
    private val accessTokenExpirationSeconds = 0

    fun genAccessToken(member: Member): String {
        val id = member.id.toLong()
        val username = member.username
        val name = member.name

        return Ut.jwt.toString(
                jwtSecretKey!!,
                accessTokenExpirationSeconds,
                mapOf("id" to id, "username" to username, "name" to name)
        )
    }

    fun payload(accessToken: String): Map<String, Any>? {
        val parsedPayload = Ut.jwt.payload(jwtSecretKey!!, accessToken)

        if (parsedPayload == null) return null

        val id = parsedPayload.get("id") as Int
        val username = parsedPayload.get("username") as String
        val name = parsedPayload.get("name") as String

        return mapOf("id" to id, "username" to username, "name" to name)
    }
}