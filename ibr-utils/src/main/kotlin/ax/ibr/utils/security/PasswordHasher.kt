package ax.ibr.utils.security

import org.mindrot.jbcrypt.BCrypt

/**
 * Utility object responsible for secure password hashing and verification
 * using BCrypt.
 *
 * The work factor (cost) used here is 12, which balances security and performance.
 */
object PasswordHasher {

    /**
     * Hashes a plain-text password using BCrypt.
     *
     * @param plain The raw password provided by the user.
     * @return A BCrypt hashed representation of the password.
     */
    fun hash(plain: String): String =
        BCrypt.hashpw(plain, BCrypt.gensalt(12))

    /**
     * Verifies whether a plain-text password matches a previously hashed value.
     *
     * This method is safe against invalid or malformed hashes; if the hash
     * cannot be parsed, it will return false instead of throwing an exception.
     *
     * @param plain The raw password to verify.
     * @param hashed The stored BCrypt hash.
     * @return true if the password matches the hash, false otherwise.
     */
    fun verify(plain: String, hashed: String): Boolean {
        return try {
            BCrypt.checkpw(plain, hashed)
        } catch (e: IllegalArgumentException) {
            // Malformed hash (e.g. legacy account with plaintext password stored)
            false
        }
    }
}