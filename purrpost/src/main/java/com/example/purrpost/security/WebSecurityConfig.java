package com.example.purrpost.security;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	// Configuring HttpSecurity
	// https://www.danvega.dev/blog/spring-security-jwt
	// @Configuration makes this class visible to spring

	// More docs: https://docs.spring.io/spring-security/reference/servlet/oauth2/index.html

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
        		.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(requests -> requests
                	.requestMatchers("/welcome", "/api/login", "/api/register",
                			"/v3/api-docs/**",
                			"/v3/api-docs.yaml",
                			"/swagger-ui/**",
                			"/swagger-ui.html").permitAll()
                	.anyRequest().authenticated()
                )
                .logout(logout -> logout.permitAll())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(Customizer.withDefaults())
                .build();
    }



    // the value we need to set in our application.yml
    @Value("${rsa.public.location}")
    private RSAPublicKey publicKey;

    @Value("${rsa.private.location}")
    private RSAPrivateKey privateKey;

    // https://docs.spring.io/spring-security/reference/servlet/oauth2/resource-server/jwt.html
    // Create JwtDecoder for Spring Security
    @Bean
    JwtDecoder jwtDecoder() {
    	return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
    	// https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/oauth2/jwt/NimbusJwtEncoder.html
    	JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
    	JWKSource<SecurityContext> jwksource = new ImmutableJWKSet<>(new JWKSet(jwk));
    	return new NimbusJwtEncoder(jwksource);
    }
}
