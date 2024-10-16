package com.example.buildingmaterialstore.config;

import com.example.buildingmaterialstore.entity.User;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private Long jwtExpirationMs;

    public String generateToken(String identifier){
        return Jwts.builder()
                .setSubject(identifier)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJwt(token);
            return true;
        }catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }

    public String getIdentifierFromToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(jwtSecret)
                .build()
                .parseClaimsJwt(token)
                .getBody().getSubject();
    }
}
