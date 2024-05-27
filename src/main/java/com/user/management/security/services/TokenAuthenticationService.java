package com.user.management.security.services;

import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.user.management.security.jwt.JwtUtils;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenAuthenticationService {
	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(TokenAuthenticationService.class);

	public List<String> authenticateToken(String token , HttpServletRequest request) {
		
		 if (token != null) {
	            token = token.trim(); // Trim any leading or trailing whitespace
	            if (jwtUtils.validateJwtToken(token)) {
	                String username = jwtUtils.getUserNameFromJwtToken(token);

	                // Retrieve user details including roles
	                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

	                // Get user's roles
	                List<String> userRoles = userDetails.getAuthorities().stream()
	                        .map(GrantedAuthority::getAuthority)
	                        .collect(Collectors.toList());
	                logger.info("User roles from TokenAuthenticationService: '{}'", userRoles);
	                
	                // Set authentication in SecurityContextHolder
	                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,
	                        null, userDetails.getAuthorities());
	                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

	                SecurityContextHolder.getContext().setAuthentication(authentication);

	                return userRoles;
	            }
	        }
	        return null; // Return null if token is invalid
	    
	}

}
