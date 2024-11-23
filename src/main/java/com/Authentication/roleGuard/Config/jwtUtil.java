package com.Authentication.roleGuard.Config;

import com.Authentication.roleGuard.DTO.userDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class jwtUtil {
     @Value("${jwt.secret}")
        private String secret;

        @Value("${jwt.expiration}")
        private int expiration;

        @Value("${jwt.authorities.key}")
        private String AUTHORITY_KEY;

        public String generateToken(userDto users) {
            Date now = new Date(System.currentTimeMillis());
            Date expiryDate = new Date(now.getTime() + expiration);
            /*String authorties = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","))*/
            /*Map<String, Object> claims = new HashMap<>();
            claims.put(authentication.getName(),"ADMIN");*/
            Map<String, Object> claim=new HashMap<>();
            claim.put("roles",users.getRoles());
            return Jwts.builder()
                    .setSubject(users.getUsername())
                    .addClaims(claim)
                    .setIssuedAt(now)
                    .setExpiration(expiryDate)
                    .signWith(SignatureAlgorithm.HS512, secret)
                    .compact();
        }

        public String extractUsername(String token) {
            return extractClaim(token, Claims::getSubject);
        }

        public Date extractExpiration(String token) {
            return extractClaim(token, Claims::getExpiration);
        }

        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
            final Claims claims = extractAllClaims(token);
            return claimsResolver.apply(claims);
        }

        private Claims extractAllClaims(String token) {
            //return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            Claims claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
            return claims;
        }

        public boolean isTokenExpired(String token) {
            final Date expiration = extractExpiration(token);
            return expiration.before(new Date());
        }

        // This method validates the authorization token
    public boolean isValidToken(String authToken) {
        try {
            if(authToken != null && authToken.startsWith("Bearer ")){
                return true;
            }
            // Example: Check if the token is not null and has a valid format
        } catch (Exception e) {
            // Log the exception or handle it as per your application's requirements
            e.printStackTrace();

        }
        // Return false in case of any exception
        return false;
    }
}
