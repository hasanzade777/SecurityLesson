package com.example.securitylesson.config.jwt;

import com.example.securitylesson.model.user;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JwtService {
    public Claims parseToken(String token){
        return Jwts.parserBuilder().
                setSigningKey(key).
                build().
                parseClaimsJws(token).
                getBody();
   }
    private Key key;
    @PostConstruct
    public void init(){
        byte[] keyBytes;
        keyBytes= Decoders.BASE64.decode("VGhpcyBpcyBteSBTZWNyZXQgS2V5IEJhc2U1MzUzNDMyMzI0");
        key= Keys.hmacShaKeyFor(keyBytes);
    }
    public String issueToken(user user, Duration duration){
        log.trace("Issue JWT token to {} for {}",user,duration);
        final JwtBuilder jwtBuilder= Jwts.builder().
                setSubject(user.getUsername()).
                setIssuedAt(new Date()).
                setExpiration(Date.from(Instant.now().plus(duration))).
                setHeader(Map.of("type","JWT")).
                addClaims(Map.of("role", List.of("STUDENT, ADMIN"))).
                signWith(key,SignatureAlgorithm.HS256);
        return jwtBuilder.compact();
    }
}
