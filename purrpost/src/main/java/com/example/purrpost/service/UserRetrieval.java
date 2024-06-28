package com.example.purrpost.service;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
public class UserRetrieval {
	// Retrieve custom claim from the JWT defined in JwtTokenService
	public static long getCurrentUserId() throws UserRetrievalException {
		SecurityContext context = SecurityContextHolder.getContext();

		if (null != context.getAuthentication()) {
			Jwt testJwt  = (Jwt) context.getAuthentication().getPrincipal();
			return testJwt.getClaim("user_id");
		} else {
			throw new UserRetrievalException("Cannot get current user id");
		}
	}

	public static String getCurrentUserRole() throws UserRetrievalException {
		SecurityContext context = SecurityContextHolder.getContext();

		if (null != context.getAuthentication()) {
			Jwt testJwt  = (Jwt) context.getAuthentication().getPrincipal();
			return testJwt.getClaimAsString("user_role");
		} else {
			throw new UserRetrievalException("Cannot get current user id");
		}
	}
}
