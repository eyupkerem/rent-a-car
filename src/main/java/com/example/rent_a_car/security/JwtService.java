package com.example.rent_a_car.security;

import com.example.rent_a_car.entity.Users;
import com.example.rent_a_car.exception.ResourceNotFoundException;
import com.example.rent_a_car.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.example.rent_a_car.utils.Validations.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final UserRepository userRepository;


    @Value("${jwt.key}")
    private String SECRET;

    public String generateToken(String email){
        Map<String , Object> claims = new HashMap<>();
        Users user = userRepository.findByEmail(email)
                .orElseThrow(()-> new ResourceNotFoundException(USER_NOT_FOUND));
        claims.put("id" , user.getId());
        return createToken(claims,email);
    }

    private String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration( new Date(System.currentTimeMillis() + 1000 *60*30))
                .signWith(SignatureAlgorithm.HS512,SECRET)
                .compact();
    }

    public boolean validateToken(String token , UserDetails userDetails){
        String username = extractEmail(token);
        Date expirationDate = extractExpiration(token);

        return userDetails.getUsername().equals(username)
                &&
                expirationDate.after(new Date()) ;
    }

    private Date extractExpiration(String token){
        try{
            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getExpiration();
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    public Long extractId(String token){
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            Long id = claims.get("id", Long.class);

            return id;
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }


    public String extractEmail(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getSubject();
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }
}
