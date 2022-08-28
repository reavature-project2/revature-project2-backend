package com.revature.util;

import com.revature.exceptions.AuthenticationException;
import com.revature.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenManager {
    private final Key key;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    Constructors
    public JwtTokenManager() {
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

//    Methods
    public String issueToken(User user) {
        return Jwts.builder()
                .setId(String.valueOf(user.getUser_id()))
                .setSubject(user.getEmail())
                .setIssuer("Rolodex API")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .signWith(key).compact();
    }

    public int parseUserIdFromToken(String token) {
        try {
            return Integer.parseInt(Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token).getBody().getId());
        } catch (Exception e) {
            logger.warn("JWT error parsing user id from token");
            throw new AuthenticationException("Your session is expired. Please sign in again.");
        }
    }
}
