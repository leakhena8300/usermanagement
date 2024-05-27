package com.user.management.security.jwt;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.filter.OncePerRequestFilter;
import com.user.management.security.services.TokenAuthenticationService;

public class AuthTokenFilter extends OncePerRequestFilter {
  
  @Autowired
  private TokenAuthenticationService tokenAuthenticationService;


  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
          throws ServletException, IOException {
	  try {
          String token = request.getHeader("Authorization");

          // Log the token value
          logger.info("Token received in AuthTokenFilter: '{}'", token);

          // Remove "Bearer " prefix and trim any whitespace
          if (token != null && token.startsWith("Bearer ")) {
        	  token = token.substring(7).trim(); 
              logger.info("Trimmed token: '{}'", token);
          }

          // Authenticate token and get user's role
          List<String> userRoles = tokenAuthenticationService.authenticateToken(token, request);
          logger.info("User roles from TokenAuthenticationService: '{}'", userRoles);

          if (!userRoles.isEmpty()) {
              logger.info("User roles in AuthTokenFilter: '{}'", userRoles);
              request.setAttribute("userRoles", userRoles);
          } else {
              logger.warn("User roles are empty after authentication.");
          }

      } catch (Exception e) {
          logger.error("Cannot set user authentication: {}", e);
      }

      filterChain.doFilter(request, response);
  }

}
