package com.school.management.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.school.management.entity.User;

@Service
public class TokenProvider {

	@Value("${security.jwt.secret-key}")
	private String JWT_SECRET;

	public String generateAccessToken(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
			return JWT.create().withSubject(user.getUsername()).withClaim("username", user.getUsername())
					.withExpiresAt(genAccessExpirationDate()).sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new JWTCreationException("Error while generating token", exception);
		}
	}

	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
			return JWT.require(algorithm).build().verify(token).getSubject();
		} catch (JWTVerificationException exception) {
			throw new JWTVerificationException("Error while validating token", exception);
		}
	}

	 public String refreshAccessToken(String refreshToken) {
	        String emailId = validateRefreshToken(refreshToken);
	        User user = new User(null, emailId, null, null);
	        return generateAccessToken(user);
	    }

	public String generateRefreshToken(User user) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
			return JWT.create().withSubject(user.getUsername()).withClaim("username", user.getUsername())
					.withExpiresAt(genRefreshExpirationDate()).sign(algorithm);
		} catch (JWTCreationException exception) {
			throw new JWTCreationException("Error while generating refresh token", exception);
		}
	}

	public String validateRefreshToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
			return JWT.require(algorithm).build().verify(token).getSubject();
		} catch (JWTVerificationException exception) {
			throw new JWTVerificationException("Error while validating refresh token", exception);
		}
	}

	private Instant genAccessExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

	private Instant genRefreshExpirationDate() {
		return LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC);
	}

}