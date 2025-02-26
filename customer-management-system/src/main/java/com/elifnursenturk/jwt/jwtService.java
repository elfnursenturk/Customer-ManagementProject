/*package com.elifnursenturk.jwt;

import java.rmi.server.ExportException;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class jwtService {
	
	public static final String SECRET_KEY = "LpwEHTpASsBOX9KDoZr/eAGOSTIJf10UBUJx8j7ZcfA=";

	public String generateToken(UserDetails userdetails) {
	return Jwts.builder().
		setSubject(userdetails.getUsername())
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis() + 1000*60*60*2))
		.signWith(getKey(), SignatureAlgorithm.HS256)
		.compact();
	}
	
	public <T> T exportToken(String token , Function<Claims, T> claimsFunction) {
	Claims claims = Jwts
		.parserBuilder()
		.setSigningKey(getKey())
		.build()
		.parseClaimsJws(token).getBody();
		return claimsFunction.apply(claims);
		
	}
	
	public String getuserNameByToken(String token) {
	return	exportToken(token, Claims::getSubject);
	}
	
	public boolean isTokenExpired(String token) {
		Date expiredDate = exportToken(token, Claims::getExpiration);
		return new Date().before(expiredDate);
	}
	

	
	public Key getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}*/
