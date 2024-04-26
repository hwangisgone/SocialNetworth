//package com.example.purrpost.security;
//
//import java.io.IOException;
//
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//@Component
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
//		
//		if (header != null && header.startsWith("Bearer ")) {
//			String[] elements = header.split(" ");
//			try {
//				SecurityContextHolder.getContext().setAuthentication(
//						userAuthProvider.validateToken(elements[1]);
//				);
//			} catch (RuntimeException e){
//				SecurityContextHolder.clearContext();
//				throw e;
//			}
//		}
//		filterChain.doFilter(request, response);
//	}
//
//}
