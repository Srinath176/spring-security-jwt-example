package dev.srinathg.spring_security_example.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.*;

@Service
public class JwtService {

    private String SECRET_KEY = "";

    public JwtService() throws NoSuchAlgorithmException {
        KeyGenerator key = KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey = key.generateKey();
        SECRET_KEY = Base64.getEncoder().encodeToString(secretKey.getEncoded());
        System.out.println(SECRET_KEY);
    }


    public String generateToken(String username) {

        Map<String,String> claims = new HashMap<>();
        claims.put("type","JWT");

        return Jwts.builder()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 30))
                .signWith(generateKey())
                .compact();
    }

    private SecretKey generateKey() {

        byte[] decodedKey = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(decodedKey);
    }

    public String extractUsername(String jwt){
        Claims claims = getClaims(jwt);
        return claims.getSubject();
    }

    private Claims getClaims(String jwt) {

        return Jwts.parser()
                .verifyWith(generateKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
    }

    public boolean isTokenValid(String jwt){
        Claims claims = getClaims(jwt);
        return claims.getExpiration().after(Date.from(Instant.now()));
    }
}
