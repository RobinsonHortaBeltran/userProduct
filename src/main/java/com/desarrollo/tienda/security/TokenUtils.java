package com.desarrollo.tienda.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.security.Key;
import java.util.*;

public class TokenUtils {

    private final static String ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJJc3N1ZXIiOiJJc3N1ZXIiLCJVc2VybmFtZSI6IkphdmFJblVzZSIsImV4cCI6MTcxMTQ5MjAzNiwiaWF0IjoxNzExNDkyMDM2fQ.5-o5FtF9V3_tDK4otAYbFkdrR3YQgPLpzrYbHVG-j7E";
    private final static Long EXPIRATION_TIME = 2_592_000L;

    public static String createToken(String username, String email){
         long expirationTime = EXPIRATION_TIME * 1_000;
         Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);
        Map<String, Object> extra = new HashMap<>();
        extra.put("nombre", username);

        return Jwts.builder()
                .setSubject(email)
                .setClaims(extra)
                .setIssuedAt(new Date())
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){

        try{
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String email = claims.getSubject();
            return new UsernamePasswordAuthenticationToken(email, null, Collections.emptyList());
        } catch (JwtException e){
            return null;
        }
    }

}
