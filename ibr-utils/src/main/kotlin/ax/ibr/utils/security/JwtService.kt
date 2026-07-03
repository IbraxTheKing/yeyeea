package ax.ibr.utils.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import java.util.Date
import javax.crypto.SecretKey

/**
 * JWT generation and validation utilities.
 *
 * In production, the JWT_SECRET environment variable must be defined.
 * Never commit or use the default secret in a production environment.
 */
object JwtService {

    private val secretKey: SecretKey = Keys.hmacShaKeyFor(
        (System.getenv("JWT_SECRET") ?: "dev-only-secret-change-me-32-chars-min").toByteArray()
    )

    private const val EXPIRATION_MS = 1000L * 60 * 60 * 24 // 24h

    fun generateToken(userId: Long, username: String, role: String): String {
        val now = Date()
        val expiry = Date(now.time + EXPIRATION_MS)

        return Jwts.builder()
            .subject(userId.toString())
            .claim("username", username)
            .claim("role", role)
            .issuedAt(now)
            .expiration(expiry)
            .signWith(secretKey)
            .compact()
    }

    fun parseToken(token: String): Claims? {
        return try {
            Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .payload
        } catch (e: JwtException) {
            null
        } catch (e: IllegalArgumentException) {
            null
        }
    }

    fun getUserId(claims: Claims): Long = claims.subject.toLong()
    fun getRole(claims: Claims): String = claims["role"] as? String ?: "USER"
    fun getUsername(claims: Claims): String = claims["username"] as? String ?: ""
}
