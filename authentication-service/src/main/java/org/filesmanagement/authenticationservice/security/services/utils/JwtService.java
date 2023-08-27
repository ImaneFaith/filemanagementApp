package org.filesmanagement.authenticationservice.security.services.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.filesmanagement.authenticationservice.security.SecurityConstants;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Date;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.function.Function;

@Service
public class JwtService {
    public String generateToken(Map<String, Object> extraClaims, String username) {
        return buildToken(extraClaims, username, SecurityConstants.EXPIRATION);
    }

    public String generateRefreshToken(Map<String, Object> extraClaims, String username){
        return buildToken(extraClaims,username, SecurityConstants.REFRESH_EXPRIRATION);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        // TODO: 8/24/2023 to implement
       return true;

    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Object extractAuthorities(String token) {
        return extractAllClaims(token).get("authorities");
    }

    private String buildToken(
            Map<String, Object> extraClaims, String username, long expiration) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuer(SecurityConstants.ISSUER)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private static Key getSignInKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SecurityConstants.sKEY);

        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());

    }

}
