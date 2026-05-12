package com.example.spring_micro.service
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService {

    private val SECRET_KEY: SecretKey =
        Keys.hmacShaKeyFor("my-secret-key-123456789012345678901234567890".toByteArray())

    // ✅ Generate Token
    fun generateToken(email: String): String {
        return Jwts.builder()
            .setSubject(email)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 1 day
            .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
            .compact()
    }

    // ✅ Extract Email
    fun extractEmail(token: String): String {
        return extractAllClaims(token).subject
    }

    // ✅ Validate Token
    fun isTokenValid(token: String, email: String): Boolean {
        val extractedEmail = extractEmail(token)
        return extractedEmail == email && !isTokenExpired(token)
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractAllClaims(token).expiration.before(Date())
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(SECRET_KEY)
            .build()
            .parseClaimsJws(token)
            .body
    }
}