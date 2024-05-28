package com.hg.hgblogback.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;


@Component
public class JwtProvider {
    
    // 보안키
    @Value("${security-Key}")
    private String securityKey;

    // JWT 생성 메서드
    public String jwtCreate(String email) {
        Date expirationDate = Date.from(Instant.now().plus(1, ChronoUnit.HOURS));
        String jwt = Jwts.builder()
            .signWith(SignatureAlgorithm.HS256, securityKey)
            .setSubject(email).setIssuedAt(new Date()).setExpiration(expirationDate)
            .compact();
        return jwt;
    }//* jwtCreate */

    // 검증 메서드
    public String verification(String jwt) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(securityKey)
                .parseClaimsJws(jwt).getBody();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return null;
        }
        return claims.getSubject();
    }//* verification */
} //* JwtProvider */
