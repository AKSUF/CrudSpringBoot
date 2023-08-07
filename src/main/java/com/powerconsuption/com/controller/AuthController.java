package com.powerconsuption.com.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.powerconsuption.com.config.JwTokenHelper;
import com.powerconsuption.com.config.JwtAuthresponse;
import com.powerconsuption.com.exception.ApiException;
import com.powerconsuption.com.payload.JauthRequest;
import com.powerconsuption.com.payload.UserDto;
import com.powerconsuption.com.service.UserDetailsService;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {

	@Autowired
	private ModelMapper mapper;
	@Autowired
	private UserDetailsService userService;
	@Autowired
	private org.springframework.security.core.userdetails.UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwTokenHelper jwTokenHelper;
	
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		UserDto registerNewUser=this.userService.registerNewUser(userDto);
		
		return new ResponseEntity<UserDto>(registerNewUser,HttpStatus.CREATED);
		
	}
	@PostMapping(value = "/login", consumes = "application/json")
	public ResponseEntity<JwtAuthresponse> creteToken(@RequestBody JauthRequest request) {
	    this.authenticate(request.getLogin_id(), request.getPassword());
	    UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getLogin_id());
	    String token = this.jwTokenHelper.generateToken(userDetails);
	    JwtAuthresponse response = new JwtAuthresponse();
	    response.setToken(token);
	    response.setUser(this.mapper.map(userDetails, UserDto.class));
	    return new ResponseEntity<JwtAuthresponse>(response, HttpStatus.OK);
	}

	private void authenticate(String username, String password) {
		UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(username, password);
		try {
			this.authenticationManager.authenticate(authentication);
		}catch(BadCredentialsException e) {
			throw new ApiException("Invalid Password and UserName");
		}
		
	}


	
	
}
