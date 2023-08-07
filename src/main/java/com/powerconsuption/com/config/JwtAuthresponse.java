package com.powerconsuption.com.config;

import com.powerconsuption.com.payload.UserDto;

import lombok.Data;

@Data
public class JwtAuthresponse {
private String token;
private UserDto user;
}
