package com._7.bookinghospital.user_service.infrastructure.jwt;

import com._7.bookinghospital.user_service.domain.model.UserRole;
import com._7.bookinghospital.user_service.domain.security.TokenProvider;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenProvider implements TokenProvider {

    @Value("${spring.application.name}")
    private String issuer;

    @Value("${service.jwt.access-expiration}")
    private Long accessExpiration;

    private SecretKey secretKey;

    public JwtTokenProvider( @Value("${service.jwt.secret-key}")String secretkey) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(secretkey));
    }

    @Override
    public String generateToken(String userId, UserRole role){
        return Jwts.builder()
                .subject(userId)
                .issuedAt(new Date(System.currentTimeMillis()))
                .issuer(issuer)
                .claim("role",role.name())
                .expiration(new Date(System.currentTimeMillis() + accessExpiration))
                .signWith(secretKey, Jwts.SIG.HS512)
                .compact();
    }
}
