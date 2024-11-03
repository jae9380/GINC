package com.example.ginc.util.auth.infrastructure;

import com.example.ginc.domain.account.domain.UserDomainEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final Environment env;

    public String generateToken(UserDomainEntity userDomainEntity, Duration expiredAt) {
        Date now = new Date();
        return createJwt(userDomainEntity, new Date(now.getTime() + expiredAt.toMillis()));
    }


    public String createJwt(UserDomainEntity userDomainEntity, Date expiry) {
        Date now = new Date();
        return Jwts.builder()
                .claim("username", userDomainEntity.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, env.getProperty("jwt.secret-key"))
                .compact();
    }

    public boolean isExpired(String token) {
        return parseClaims(token)
                .getExpiration()
                .before(new Date());
    }

    public String getUsername(String token) {
        return parseClaims(token)
                .get("username", String.class);
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .setSigningKey(env.getProperty("jwt.secret-key"))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}