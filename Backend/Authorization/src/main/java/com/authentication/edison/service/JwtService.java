package com.authentication.edison.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.authentication.edison.model.UserDetail;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

	@Value("${secretkey}")
    private String SECRETKEY; //from resourse 
	
	@Value("${tokenduration}")
	private int TOKENDURATION;

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
    	String formated_token = token.trim().replaceAll("\0xfffd", "");
        return Jwts.parser().setSigningKey(SECRETKEY).parseClaimsJws(formated_token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetail userDetail) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetail.getUsername()); //payload
    }

    private String createToken(Map<String, Object> claims, String username) {

        return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKENDURATION*30*1000))
                .signWith(SignatureAlgorithm.HS256, SECRETKEY).compact();
    }

    public Boolean validateToken(String token, UserDetail userDetail) {
        final String username = extractUsername(token);
        System.out.println(username);
        return (username.equals(userDetail.getUsername()) && !isTokenExpired(token));
    }
}