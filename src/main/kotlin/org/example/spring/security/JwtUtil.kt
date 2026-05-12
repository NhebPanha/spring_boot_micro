package com.example.spring_micro.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtil {

    // Using a securely generated key for HS256
    private val key: SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256)
    
    @Value("\${jwt.expiration:86400000}") // 1 day default
    private val expirationTime: Long = 86400000

    fun generateToken(username: String, role: String): String {
        return Jwts.builder()
            .setSubject(username)
            .claim("role", role)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + expirationTime))
            .signWith(key)
            .compact()
    }

    fun extractUsername(token: String): String {
        return extractAllClaims(token).subject
    }

    fun extractRole(token: String): String {
        return extractAllClaims(token)["role"] as String
    }

    fun isTokenValid(token: String, username: String): Boolean {
        val extractedUsername = extractUsername(token)
        return (extractedUsername == username && !isTokenExpired(token))
    }

    private fun isTokenExpired(token: String): Boolean {
        return extractAllClaims(token).expiration.before(Date())
    }

    private fun extractAllClaims(token: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token)
            .body
    }
}
