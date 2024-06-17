package com.example.purrpost.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.example.purrpost.model.SocialUser;

// IDK WHAT @Service DO
@Service
public class JwtTokenService {
	private final JwtEncoder encoder;

	public JwtTokenService(JwtEncoder encoder) {
		this.encoder = encoder;
	}

	public String generateToken(SocialUser user) {
		Instant now = Instant.now();

		// Reference: https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/index.html#servlet-authentication-unpwd
		Authentication auth = UsernamePasswordAuthenticationToken.unauthenticated(user.getUserId(), user.getPassword());

		// authRequest.setAuthenticated(true);

		String scope = auth.getAuthorities().stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.joining(" "));

		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("self")
				.issuedAt(now)
				.expiresAt(now.plus(1, ChronoUnit.DAYS))
				.subject(auth.getName())
				.claim("scope", scope)
				.claim("user_id", user.getUserId())		// Custom claim
				.claim("user_role", user.getRole())
				.build();

		return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

}
