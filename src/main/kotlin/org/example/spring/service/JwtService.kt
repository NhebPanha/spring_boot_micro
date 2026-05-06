package com.example.spring_micro.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService {

    private val SECRET = "my-secret-key-123456"

    fun generateToken(email: String): String {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hour
            .signWith(SignatureAlgorithm.HS256, SECRET.toByteArray())
            .compact()
    }

    fun extractEmail(token: String): String {
        return Jwts.parser()
            .setSigningKey(SECRET.toByteArray())
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun isTokenValid(token: String): Boolean {
        return try {
            val claims = Jwts.parser()
                .setSigningKey(SECRET.toByteArray())
                .parseClaimsJws(token)

            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }
}