package com.kholanhcaonguyen.PMQL.service;

import com.kholanhcaonguyen.PMQL.entity.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret; // Secret key cho JWT

    @Value("${jwt.expiration}")
    private long jwtExpirationInMs; // Thời gian hết hạn token

    public String generateToken(AppUser appUser) {
        return Jwts.builder()
                .setSubject(appUser.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationInMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }
}
