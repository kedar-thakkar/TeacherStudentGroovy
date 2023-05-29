package com.ts.security


import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

import java.util.function.Function

@Component
class JwtUtil implements Serializable {

    private final String secret = "techerstudentapplicationsecret";
    private final long expiryDuration = 5 * 60 * 60;
    long milliTime = System.currentTimeMillis();
    long expiryTime = milliTime + expiryDuration * 1000;


    String generateTokenFromUsername(String username) {
        return Jwts.builder().setSubject(username).setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expiryTime))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    Boolean validateToken(String token, UserDetails userDetails) {
        final String name = getUsernameFromToken(token);
        return (name.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}