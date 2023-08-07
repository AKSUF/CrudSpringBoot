package com.powerconsuption.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.powerconsuption.com.entity.User;
import com.powerconsuption.com.exception.ResourceNotFounException;
import com.powerconsuption.com.repository.UserRepository;
@Service
public class CustomUserDetailService implements UserDetailsService {
@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
User user=this.userRepository.findByEmail(username).orElseThrow(()->new ResourceNotFounException("User","email:"+username,0));
		
		return user;
	}

}
