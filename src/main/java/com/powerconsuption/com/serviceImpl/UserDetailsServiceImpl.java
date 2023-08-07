package com.powerconsuption.com.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.powerconsuption.com.entity.User;
import com.powerconsuption.com.payload.UserDto;
import com.powerconsuption.com.repository.UserRepository;
import com.powerconsuption.com.service.UserDetailsService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private ModelMapper modelmapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto registerNewUser(UserDto userDto) {
	User user=this.modelmapper.map(userDto,User.class);
	user.setPassword(this.passwordEncoder.encode(user.getPassword()));
	User newUser=this.userRepository.save(user);
	return this.modelmapper.map(newUser, UserDto.class);
	}



}
