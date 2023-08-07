package com.powerconsuption.com.config;


import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwAuthenticationFilter extends OncePerRequestFilter {
@Autowired
	private UserDetailsService userdetailservice;
@Autowired
private JwTokenHelper jwTokenhelper;
@Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException, ExpiredJwtException, MalformedJwtException{

	String requestToken=request.getHeader("Authorization");
	//get token
	System.out.println(requestToken);
	String username=null;
	String token=null;
	
	if(requestToken !=null && requestToken.startsWith("Bearer")) {
		
		token=requestToken.substring(7);
		try {
			
		
		username=this.jwTokenhelper.getUsernameFromToken(token);
		}catch(Exception e) {
			System.out.println("unable to get Jwt token");
		}
	}else {
		System.out.println("Invalid JWT Exceptions");
	}
	
	//once we get the validator ,now validates
	
	if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
		
		UserDetails userDetails=this.userdetailservice.loadUserByUsername(username);
		
		if(this.jwTokenhelper.validateToken(token,userDetails)) {
UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			
		}
		else {
			System.out.println("Incvalid jwt token");
		}
	}else {
		System.out.println("username is null");
	}
	filterChain.doFilter(request,response);
}

}
