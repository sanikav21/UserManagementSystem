package com.example.UserManagement.Security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter 
	extends OncePerRequestFilter {

	    @Autowired
	    private JwtUtil jwtUtil;

	    @Autowired
	    private CustomUserDetailsService userService;

	    @Override
	    protected void doFilterInternal(

	            HttpServletRequest request,

	            HttpServletResponse response,

	            FilterChain filterChain

	    ) throws ServletException, IOException {
	    	
	    	String path =
	                request.getServletPath();

	        if(path.startsWith("/auth")) {

	            filterChain.doFilter(
	                    request,
	                    response
	            );

	            return;
	        }

	        String header =

	                request.getHeader(
	                        "Authorization"
	                );

	        String token = null;

	        String email = null;

	        if(header != null

	                &&

	                header.startsWith(
	                        "Bearer "
	                )) {

	            token = header.substring(
	                    7
	            );

	            email = jwtUtil.extractEmail(
	                    token
	            );
	        }

	        if(email != null

	                &&

	                SecurityContextHolder

	                        .getContext()

	                        .getAuthentication()

	                        == null) {

	            UserDetails details =

	                    userService

	                            .loadUserByUsername(
	                                    email
	                            );

	            if(jwtUtil.validateToken(
	                    token
	            )) {

	                UsernamePasswordAuthenticationToken auth =

	                        new UsernamePasswordAuthenticationToken(

	                                details,

	                                null,

	                                details.getAuthorities()
	                        );

	                auth.setDetails(

	                        new WebAuthenticationDetailsSource()

	                                .buildDetails(
	                                        request
	                                )
	                );

	                SecurityContextHolder

	                        .getContext()

	                        .setAuthentication(
	                                auth
	                        );
	            }
	        }

	        filterChain.doFilter(
	                request,
	                response
	        );
	    }
	}
	


